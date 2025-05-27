package com.quizgame.quizgame.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player1_id", nullable = false)
    private Long player1Id;

    @Column(name = "player2_id", nullable = false)
    private Long player2Id;

    @Column(name = "status")
    private String status;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime endTime;

    @Column(name = "winner_id")
    private Long winnerId;

    // Связь с пользователем (игрок 1)
    @ManyToOne
    @JoinColumn(name = "player1_id", insertable = false, updatable = false)
    private User player1;

    // Связь с пользователем (игрок 2)
    @ManyToOne
    @JoinColumn(name = "player2_id", insertable = false, updatable = false)
    private User player2;

    // Вопросы матча
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "match_questions",
        joinColumns = @JoinColumn(name = "match_id"),
        inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> questions = new HashSet<>();

    // Геттеры и сеттеры для player1 и player2
    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }

    // Геттер и сеттер для questions
    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}