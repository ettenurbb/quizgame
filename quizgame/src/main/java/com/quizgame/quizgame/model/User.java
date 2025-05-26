package com.quizgame.quizgame.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String passwordHash;
    
    @Column(name = "email_verified")
    private boolean emailVerified = false;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "token_expiration")
    private LocalDateTime tokenExpiration;

    private String avatarUrl;

    private int eloRating = 1200;

    private int totalQuizzes;

    public String getEmail() {
        return email;
    }
}