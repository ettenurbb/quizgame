package com.quizgame.quizgame.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "match_results")
public class MatchResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int correctAnswersCount;

    private int timeLeft;
}