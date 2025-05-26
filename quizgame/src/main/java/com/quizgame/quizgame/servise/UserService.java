package com.quizgame.quizgame.service;

import com.quizgame.quizgame.model.User;
import com.quizgame.quizgame.repository.UserRepository;
import com.quizgame.quizgame.util.JwtUtil;
import com.quizgame.quizgame.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

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

        // Хэшируем пароль перед сохранением
        user.setPasswordHash(jwtUtil.encodePassword(user.getPasswordHash()));

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
        user.setVerificationToken(null);
        userRepository.save(user);
    }

    // Проверка существования пользователя
    public boolean isUserExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // Получить всех пользователей
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Заблокировать пользователя
    public void blockUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        user.setBlocked(true);
        userRepository.save(user);
    }

    // Разблокировать пользователя
    public void unblockUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        user.setBlocked(false);
        userRepository.save(user);
    }

    // Удалить пользователя
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("Пользователь не найден");
        }
        userRepository.deleteById(userId);
    }

    // Получить пользователя по email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    // Обновление данных пользователя
    public User updateUser(User updatedUser) {
        User existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        existingUser.setNickname(updatedUser.getNickname());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPasswordHash(updatedUser.getPasswordHash());
        existingUser.setAvatarUrl(updatedUser.getAvatarUrl());
        return userRepository.save(existingUser);
    }

    // Получить профиль пользователя
    public User getProfile(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }
}