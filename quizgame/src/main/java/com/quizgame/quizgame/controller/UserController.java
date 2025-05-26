package com.quizgame.quizgame.controller;

import com.quizgame.quizgame.model.User;
import com.quizgame.quizgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private com.quizgame.quizgame.service.EmailService emailService;

    // Регистрация нового пользователя
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerUser(user);
        emailService.sendVerificationEmail(user.getEmail(), user.getVerificationToken());
        return ResponseEntity.ok("Регистрация успешна. Проверьте email для подтверждения.");
    }

    // Подтверждение email по токену
    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
        userService.verifyEmail(token);
        return ResponseEntity.ok("<h1>Email успешно подтверждён!</h1>");
    }
}