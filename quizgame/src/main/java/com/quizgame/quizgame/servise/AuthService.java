package com.quizgame.quizgame.service;

import com.quizgame.quizgame.dto.LoginRequest;
import com.quizgame.quizgame.dto.LoginResponse;
import com.quizgame.quizgame.model.User;
import com.quizgame.quizgame.repository.UserRepository;
import com.quizgame.quizgame.util.JwtUtil; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Неверный пароль");
        }
        String token = jwtUtil.generateVerificationToken(user.getEmail());
        return new LoginResponse(token);
    }
}