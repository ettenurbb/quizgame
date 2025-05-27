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
    private final AnswerRepository answerRepository;

    public Match startMatch(Long player1Id, Long player2Id) {
        User player1 = userRepository.findById(player1Id).orElseThrow(() -> new RuntimeException("Игрок 1 не найден"));
        User player2 = userRepository.findById(player2Id).orElseThrow(() -> new RuntimeException("Игрок 2 не найден"));

        if (player1.isBlocked() || player2.isBlocked()) {
            throw new RuntimeException("Один из игроков заблокирован");
        }

        Match match = new Match();
        match.setPlayer1(player1);
        match.setPlayer2(player2);
        match.setStatus("in_progress");
        match.setStartTime(LocalDateTime.now());

        List<Question> easyQuestions = questionRepository.findByDifficulty(1);
        List<Question> mediumQuestions = questionRepository.findByDifficulty(2);
        List<Question> hardQuestions = questionRepository.findByDifficulty(3);

        Collections.shuffle(easyQuestions);
        Collections.shuffle(mediumQuestions);
        Collections.shuffle(hardQuestions);

        List<Question> questions = new ArrayList<>();
        questions.addAll(easyQuestions.subList(0, Math.min(3, easyQuestions.size())));
        questions.addAll(mediumQuestions.subList(0, Math.min(4, mediumQuestions.size())));
        questions.addAll(hardQuestions.subList(0, Math.min(3, hardQuestions.size())));

        match.setQuestions(new HashSet<>(questions)); // Исправлено: List -> Set
        return matchRepository.save(match);
    }

    public void processAnswer(AnswerSubmissionRequest request) {
        Match match = matchRepository.findById(request.getMatchId()).orElseThrow(() -> new RuntimeException("Матч не найден"));
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        Question question = questionRepository.findById(request.getQuestionId()).orElseThrow(() -> new RuntimeException("Вопрос не найден"));

        MatchAnswer answer = matchAnswerRepository.findByMatchAndUserAndQuestion(match, user, question).orElseGet(() -> {
            MatchAnswer newAnswer = new MatchAnswer();
            newAnswer.setMatch(match);
            newAnswer.setUser(user);
            newAnswer.setQuestion(question);
            return newAnswer;
        });

        if (answer.isAnswered()) return;

        Answer selectedAnswer = answerRepository.findById(request.getSelectedAnswerId())
                .orElseThrow(() -> new RuntimeException("Ответ не найден"));

        answer.setSelectedAnswer(selectedAnswer);
        answer.setAnswered(true); // Исправлено: setIsAnswered → setAnswered
        answer.setCorrect(selectedAnswer.isCorrect()); // setIsCorrect → setCorrect
        answer.setTimeSpent(request.getTimeTaken()); // getTimeSpent → getTimeTaken

        matchAnswerRepository.save(answer);

        checkIfAllAnswered(match, question);
    }

    private void checkIfAllAnswered(Match match, Question question) {
        List<MatchAnswer> answers = matchAnswerRepository.findByMatchAndQuestion(match, question); // Требуется метод в репозитории
        boolean allAnswered = answers.stream().allMatch(MatchAnswer::isAnswered);

        if (allAnswered) {
            finishMatch(match.getId());
        }
    }

    public void finishMatch(Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Матч не найден"));
        List<MatchAnswer> answers = matchAnswerRepository.findByMatch(match);
        Map<Long, Integer> correctAnswers = new HashMap<>();

        for (MatchAnswer ans : answers) {
            if (ans.isCorrect()) {
                correctAnswers.merge(ans.getUser().getId(), 1, Integer::sum);
            }
        }

        updateRatings(match, correctAnswers);
        match.setStatus("finished");
        match.setEndTime(LocalDateTime.now());
        matchRepository.save(match);
    }

    private void updateRatings(Match match, Map<Long, Integer> correctAnswers) {
        Long p1Id = match.getPlayer1().getId();
        Long p2Id = match.getPlayer2().getId();

        int c1 = correctAnswers.getOrDefault(p1Id, 0);
        int c2 = correctAnswers.getOrDefault(p2Id, 0);

        User winner = null;
        if (c1 > c2) {
            winner = match.getPlayer1();
        } else if (c2 > c1) {
            winner = match.getPlayer2();
        }

        int expectedScoreP1 = calculateExpectedScore(match.getPlayer1().getEloRating(), match.getPlayer2().getEloRating());
        int expectedScoreP2 = calculateExpectedScore(match.getPlayer2().getEloRating(), match.getPlayer1().getEloRating());

        int actualScoreP1 = c1 > c2 ? 1 : (c1 == c2 ? 0 : -1);
        int actualScoreP2 = c2 > c1 ? 1 : (c1 == c2 ? 0 : -1);

        updatePlayerRating(match.getPlayer1(), expectedScoreP1, actualScoreP1);
        updatePlayerRating(match.getPlayer2(), expectedScoreP2, actualScoreP2);
    }

    private int calculateExpectedScore(int ratingA, int ratingB) {
        return (int) Math.round(1 / (1 + Math.pow(10, (ratingB - ratingA) / 400.0)));
    }

    private void updatePlayerRating(User player, int expected, int actual) {
        int oldRating = player.getEloRating();
        int newRating = oldRating + 32 * (actual - expected);
        player.setEloRating(newRating);
        userRepository.save(player);

        EloHistory history = new EloHistory();
        history.setUser(player);
        history.setOldRating(oldRating);
        history.setNewRating(newRating);
        history.setChangeReason("Изменение после матча");
        history.setChangedAt(LocalDateTime.now());
        eloHistoryRepository.save(history);
    }

    public Question getNextQuestionForUser(Long userId, Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Матч не найден"));
        List<Question> questions = new ArrayList<>(match.getQuestions());
        List<MatchAnswer> answers = matchAnswerRepository.findByMatch(match);

        Set<Long> answeredIds = new HashSet<>();
        for (MatchAnswer ans : answers) {
            if (ans.isAnswered() && ans.getUser().getId().equals(userId)) {
                answeredIds.add(ans.getQuestion().getId());
            }
        }

        for (Question q : questions) {
            if (!answeredIds.contains(q.getId())) {
                return q;
            }
        }
        return null;
    }

    public List<Long> getPlayersInMatch(Long matchId) {
        Match match = matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Матч не найден"));
        return Arrays.asList(match.getPlayer1().getId(), match.getPlayer2().getId());
    }

    public Match getMatchById(Long matchId) {
        return matchRepository.findById(matchId).orElseThrow(() -> new RuntimeException("Матч не найден"));
    }

    public boolean isMatchFinished(Long matchId) {
        Optional<Match> matchOpt = matchRepository.findById(matchId);
        return matchOpt.map(m -> "finished".equals(m.getStatus())).orElse(true);
    }

    public Optional<Match> findById(Long matchId) {
        return matchRepository.findById(matchId);
    }
}