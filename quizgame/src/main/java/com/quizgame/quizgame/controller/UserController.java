package com.quizgame.quizgame.controller;

import com.quizgame.quizgame.dto.LoginRequest;
import com.quizgame.quizgame.model.User;
import com.quizgame.quizgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("Регистрация успешна. Проверьте email для подтверждения.");
    }

    @PutMapping("/{userId}/avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable Long userId, @RequestBody Map<String, String> payload) {
        String avatarUrl = payload.get("avatarUrl");
        userService.updateAvatar(userId, avatarUrl);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/promote/{userId}")
    public ResponseEntity<?> promoteToAdmin(@PathVariable Long userId) {
        userService.promoteToAdmin(userId);
        return ResponseEntity.ok("Пользователь повышен до администратора");
    }

    @PostMapping("/demote/{userId}")
    public ResponseEntity<?> demoteFromAdmin(@PathVariable Long userId) {
        userService.demoteFromAdmin(userId);
        return ResponseEntity.ok("Пользователь понижен до обычного пользователя");
    }

    @PostMapping("/block/{userId}")
    public ResponseEntity<?> blockUser(@PathVariable Long userId) {
        userService.blockUser(userId);
        return ResponseEntity.ok("Пользователь заблокирован");
    }

    @PostMapping("/unblock/{userId}")
    public ResponseEntity<?> unblockUser(@PathVariable Long userId) {
        userService.unblockUser(userId);
        return ResponseEntity.ok("Пользователь разблокирован");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}