package com.quizgame.quizgame.repository;

import com.quizgame.quizgame.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByDifficulty(int difficulty);
}