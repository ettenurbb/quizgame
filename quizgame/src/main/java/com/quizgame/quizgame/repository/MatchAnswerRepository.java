package com.quizgame.quizgame.repository;

import com.quizgame.quizgame.model.Match;
import com.quizgame.quizgame.model.MatchAnswer;
import com.quizgame.quizgame.model.Question;
import com.quizgame.quizgame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MatchAnswerRepository extends JpaRepository<MatchAnswer, Long> {
    
    List<MatchAnswer> findByUser(User user);

    Optional<MatchAnswer> findByMatchAndUserAndQuestion(Match match, User user, Question question);

    boolean existsByMatchAndUserAndQuestion(Match match, User user, Question question);

    List<MatchAnswer> findByMatch(Match match);

}