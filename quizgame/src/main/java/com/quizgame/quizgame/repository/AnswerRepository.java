package com.quizgame.quizgame.repository;

import com.quizgame.quizgame.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}