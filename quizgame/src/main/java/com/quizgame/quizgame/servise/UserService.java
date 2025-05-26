package com.quizgame.quizgame.service;

import com.quizgame.quizgame.model.User;
import com.quizgame.quizgame.repository.UserRepository;
import com.quizgame.quizgame.util.JwtUtil;
import com.quizgame.quizgame.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    // Регистрация нового пользователя
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        String token = jwtUtil.generateVerificationToken(user.getEmail());
        user.setVerificationToken(token);
        user.setEmailVerified(false);

        return userRepository.save(user);
    }

    // Подтверждение email по токену
    public void verifyEmail(String token) {
        if (!jwtUtil.isTokenValid(token)) {
            throw new RuntimeException("Неверный или истёкший токен");
        }

        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (user.isEmailVerified()) {
            throw new RuntimeException("Email уже подтверждён");
        }

        user.setEmailVerified(true);
        user.setVerificationToken(null); // Убираем токен после использования
        userRepository.save(user);
    }

    // Проверка, существует ли пользователь
    public boolean isUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}