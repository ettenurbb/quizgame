package com.quizgame.quizgame.controller;

import com.quizgame.quizgame.dto.LoginRequest;
import com.quizgame.quizgame.dto.LoginResponse;
import com.quizgame.quizgame.model.User;
import com.quizgame.quizgame.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Вход в систему
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}