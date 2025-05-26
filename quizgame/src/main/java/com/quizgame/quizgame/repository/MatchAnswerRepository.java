package com.quizgame.quizgame.repository;

import com.quizgame.quizgame.model.Match;
import com.quizgame.quizgame.model.MatchAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatchAnswerRepository extends JpaRepository<MatchAnswer, Long> {
    List<MatchAnswer> findByMatch(Match match);
}