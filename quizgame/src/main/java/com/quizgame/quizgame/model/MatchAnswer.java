package com.quizgame.quizgame.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "match_answers")
public class MatchAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "selected_answer_id")
    private Answer selectedAnswer; // ссылка на выбранный ответ

    private boolean isCorrect;
    private double timeSpent; // время ответа в секундах
    private boolean isAnswered;

    
    public void setSelectedAnswer(Answer answer) {
        this.selectedAnswer = answer;
    }
}