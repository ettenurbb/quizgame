package com.quizgame.quizgame.controller;

import com.quizgame.quizgame.dto.AnswerSubmissionRequest;
import com.quizgame.quizgame.websocket.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

@Controller
public class WebSocketController {

    @Autowired
    private WebSocketHandler webSocketHandler;

    @MessageMapping("/user/connect")
    public void connect(@Payload Map<String, Long> payload, WebSocketSession session) {
        Long userId = payload.get("userId");
        webSocketHandler.registerUser(userId, session);
    }

    @MessageMapping("/match/answer")
    public void receiveAnswer(@Payload AnswerSubmissionRequest request) {
        webSocketHandler.handleAnswer(request);
    }
}