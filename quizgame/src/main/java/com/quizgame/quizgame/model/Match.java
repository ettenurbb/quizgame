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

    private String endTime;

    // Поле для победителя
    private Long winnerId;

    // Геттер и сеттер (Lombok может не сгенерировать их корректно, если поле не аннотировано)
    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }
}