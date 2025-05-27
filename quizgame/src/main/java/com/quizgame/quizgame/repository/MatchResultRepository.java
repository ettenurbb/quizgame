package com.quizgame.quizgame.repository;

import com.quizgame.quizgame.model.MatchResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchResultRepository extends JpaRepository<MatchResult, Long> {
}