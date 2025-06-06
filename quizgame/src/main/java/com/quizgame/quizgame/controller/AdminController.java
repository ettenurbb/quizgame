package com.quizgame.quizgame.controller;

import com.quizgame.quizgame.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("Пользователь удалён");
    }

    @PostMapping("/block/{userId}")
    public ResponseEntity<String> blockUser(@PathVariable Long userId) {
        userService.blockUser(userId);
        return ResponseEntity.ok("Пользователь заблокирован");
    }

    @PostMapping("/unblock/{userId}")
    public ResponseEntity<String> unblockUser(@PathVariable Long userId) {
        userService.unblockUser(userId);
        return ResponseEntity.ok("Пользователь разблокирован");
    }
}