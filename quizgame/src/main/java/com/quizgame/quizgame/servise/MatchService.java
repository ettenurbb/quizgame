package com.quizgame.quizgame.service;

import com.quizgame.quizgame.model.*;
import com.quizgame.quizgame.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchAnswerRepository matchAnswerRepository;

    @Autowired
    private EloHistoryRepository eloHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Transactional
    public Match startMatch(Long userId1, Long userId2) {
        Match match = new Match();
        match.setPlayer1(userRepository.findById(userId1).orElseThrow());
        match.setPlayer2(userRepository.findById(userId2).orElseThrow());
        match.setStatus("IN_PROGRESS");
        match.setStartTime(LocalDateTime.now().toString());

        return matchRepository.save(match);
    }

    @Transactional
    public void finishMatch(Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow();
        List<MatchAnswer> answers = matchAnswerRepository.findByMatch(match);

        Map<Long, Integer> correctAnswers = new HashMap<>();
        Map<Long, Double> totalTimes = new HashMap<>();

        for (MatchAnswer answer : answers) {
            if (answer.isCorrect()) {
                correctAnswers.merge(answer.getUser().getId(), 1, Integer::sum);
            }
            totalTimes.merge(answer.getUser().getId(), answer.getTimeSpent(), Double::sum);
        }

        Long winnerId = null;
        if (correctAnswers.getOrDefault(match.getPlayer1().getId(), 0) > correctAnswers.getOrDefault(match.getPlayer2().getId(), 0)) {
            winnerId = match.getPlayer1().getId();
        } else if (correctAnswers.getOrDefault(match.getPlayer2().getId(), 0) > correctAnswers.getOrDefault(match.getPlayer1().getId(), 0)) {
            winnerId = match.getPlayer2().getId();
        }

        match.setStatus("FINISHED");
        match.setEndTime(LocalDateTime.now().toString());
        match.setWinnerId(winnerId);
        matchRepository.save(match);

        updateRatings(match, correctAnswers);
    }

    private void updateRatings(Match match, Map<Long, Integer> correctAnswers) {
        User player1 = match.getPlayer1();
        User player2 = match.getPlayer2();

        int scoreA = correctAnswers.getOrDefault(player1.getId(), 0);
        int scoreB = correctAnswers.getOrDefault(player2.getId(), 0);

        double expectedA = 1 / (1 + Math.pow(10, (player2.getEloRating() - player1.getEloRating()) / 400.0));
        double expectedB = 1 / (1 + Math.pow(10, (player1.getEloRating() - player2.getEloRating()) / 400.0));

        int resultA = scoreA > scoreB ? 1 : scoreA == scoreB ? 1 : 0;
        int resultB = scoreB > scoreA ? 1 : scoreA == scoreB ? 1 : 0;

        int kFactor = 32;

        int newRatingA = player1.getEloRating() + (int) Math.round(kFactor * (resultA - expectedA));
        int newRatingB = player2.getEloRating() + (int) Math.round(kFactor * (resultB - expectedB));

        saveEloHistory(player1, player1.getEloRating(), newRatingA, "After match ID: " + match.getId());
        saveEloHistory(player2, player2.getEloRating(), newRatingB, "After match ID: " + match.getId());

        player1.setEloRating(newRatingA);
        player2.setEloRating(newRatingB);
        userRepository.save(player1);
        userRepository.save(player2);
    }

    private void saveEloHistory(User user, int oldRating, int newRating, String reason) {
        EloHistory history = new EloHistory();
        history.setUser(user);
        history.setOldRating(oldRating);
        history.setNewRating(newRating);
        history.setChangeReason(reason);
        history.setChangedAt(LocalDateTime.now());
        eloHistoryRepository.save(history);
    }
}