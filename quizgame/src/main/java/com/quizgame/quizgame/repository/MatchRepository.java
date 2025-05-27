package com.quizgame.quizgame.repository;

import com.quizgame.quizgame.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}