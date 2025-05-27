package com.quizgame.quizgame.service;

import com.quizgame.quizgame.dto.AnswerSubmissionRequest;
import com.quizgame.quizgame.model.*;
import com.quizgame.quizgame.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
@AllArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final MatchAnswerRepository matchAnswerRepository;
    private final EloHistoryRepository eloHistoryRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(() -> new RuntimeException("Матч не найден"));
    }

    public void processAnswer(AnswerSubmissionRequest request) {
        Match match = matchRepository.findById(request.getMatchId()).orElseThrow(() -> new RuntimeException("Матч не найден"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Question question = questionRepository.findById(request.getQuestionId()).orElseThrow(() -> new RuntimeException("Вопрос не найден"));

        Optional<MatchAnswer> existingAnswer = matchAnswerRepository.findByMatchAndUserAndQuestion(match, user, question);
        if (existingAnswer.isPresent()) {
            throw new RuntimeException("Вы уже отвечали на этот вопрос");
        }

        MatchAnswer answer = new MatchAnswer();
        answer.setMatch(match);
        answer.setUser(user);
        answer.setQuestion(question);
        answer.setSelectedAnswer(request.getSelectedAnswerId() != null ? answerRepository.findById(request.getSelectedAnswerId()).orElse(null) : null);
        answer.setTimeSpent(request.getTimeTaken());
        answer.setIsAnswered(true);
        answer.setCorrect(answer.getSelectedAnswer() != null && answer.getSelectedAnswer().isCorrect());

        matchAnswerRepository.save(answer);

        List<MatchAnswer> answers = matchAnswerRepository.findByMatch(match);
        Set<Long> answeredIds = new HashSet<>();
        for (MatchAnswer ans : answers) {
            if (ans.isAnswered()) {
                answeredIds.add(ans.getQuestion().getId());
            }
        }

        if (answeredIds.size() == 10) {
            finishMatch(match, answers);
        }
    }

    private void finishMatch(Match match, List<MatchAnswer> answers) {
        Map<Long, Integer> correctAnswers = new HashMap<>();
        for (MatchAnswer ans : answers) {
            if (ans.isCorrect()) {
                correctAnswers.merge(ans.getUser().getId(), 1, Integer::sum);
            }
        }

        Long winnerId = determineWinner(correctAnswers, match);
        match.setStatus("FINISHED");
        match.setEndTime(LocalDateTime.now().toString());
        match.setWinnerId(winnerId);
        matchRepository.save(match);

        updateRatings(match, correctAnswers);
    }

    private Long determineWinner(Map<Long, Integer> correctAnswers, Match match) {
        int scoreA = correctAnswers.getOrDefault(match.getPlayer1().getId(), 0);
        int scoreB = correctAnswers.getOrDefault(match.getPlayer2().getId(), 0);

        if (scoreA > scoreB) return match.getPlayer1().getId();
        else if (scoreB > scoreA) return match.getPlayer2().getId();
        else return null;
    }

    private void updateRatings(Match match, Map<Long, Integer> correctAnswers) {
        User player1 = match.getPlayer1();
        User player2 = match.getPlayer2();

        int s1 = correctAnswers.getOrDefault(player1.getId(), 0);
        int s2 = correctAnswers.getOrDefault(player2.getId(), 0);

        double e1 = 1 / (1 + Math.pow(10, (player2.getEloRating() - player1.getEloRating()) / 400.0));
        double e2 = 1 / (1 + Math.pow(10, (player1.getEloRating() - player2.getEloRating()) / 400.0));

        int k1 = getKFactor(player1.getEloRating());
        int k2 = getKFactor(player2.getEloRating());

        int newRating1 = player1.getEloRating() + (int) (k1 * (s1 - e1));
        int newRating2 = player2.getEloRating() + (int) (k2 * (s2 - e2));

        player1.setEloRating(newRating1);
        player2.setEloRating(newRating2);

        saveEloHistory(player1, player1.getEloRating(), newRating1, "Match result");
        saveEloHistory(player2, player2.getEloRating(), newRating2, "Match result");

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

    private int getKFactor(int rating) {
        if (rating < 1200) return 40;
        else if (rating < 1600) return 32;
        else return 24;
    }
}