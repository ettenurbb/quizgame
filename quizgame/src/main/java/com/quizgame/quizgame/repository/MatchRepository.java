package com.quizgame.quizgame.repository;

import com.quizgame.quizgame.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByStatus(String status);
}