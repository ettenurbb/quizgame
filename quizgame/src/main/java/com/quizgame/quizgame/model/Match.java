package com.quizgame.quizgame.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player1_id")
    private User player1;

    @ManyToOne
    @JoinColumn(name = "player2_id")
    private User player2;

    private String status; // WAITING, IN_PROGRESS, FINISHED

    private String startTime;
}