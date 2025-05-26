package com.quizgame.quizgame.service;

import com.quizgame.quizgame.dto.LoginRequest;
import com.quizgame.quizgame.dto.LoginResponse;
import com.quizgame.quizgame.model.User;
import com.quizgame.quizgame.repository.UserRepository;
import com.quizgame.quizgame.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Неверный пароль");
        }

        if (!user.isEmailVerified()) {
            throw new RuntimeException("Email не подтверждён");
        }

        String token = jwtUtil.generateVerificationToken(user.getEmail());
        return new LoginResponse(token);
    }
}