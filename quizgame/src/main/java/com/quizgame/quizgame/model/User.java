package com.quizgame.quizgame.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String email;
    private String passwordHash;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "elo_rating", columnDefinition = "INT DEFAULT 1200")
    private int eloRating = 1200;

    @Column(name = "total_quizzes", columnDefinition = "INT DEFAULT 0")
    private int totalQuizzes = 0;

    @Column(name = "email_verified", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean emailVerified = false;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "token_expiration")
    private java.time.LocalDateTime tokenExpiration;

    @Column(name = "is_blocked", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean blocked = false;

    @Column(name = "role")
    private String role = "ROLE_USER";
}