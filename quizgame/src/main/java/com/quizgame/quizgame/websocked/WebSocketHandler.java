package com.quizgame.quizgame.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quizgame.quizgame.dto.AnswerSubmissionRequest;
import com.quizgame.quizgame.model.Match;
import com.quizgame.quizgame.model.Question;
import com.quizgame.quizgame.service.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@AllArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final MatchService matchService;
    private final ObjectMapper objectMapper;
    private final Map<Long, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void registerUser(Long userId, WebSocketSession session) {
        sessions.put(userId, session);
    }

    public void unregisterUser(Long userId) {
        sessions.remove(userId);
    }

    public void handleAnswer(AnswerSubmissionRequest request) {
        try {
            matchService.processAnswer(request);

            if (!matchService.isMatchFinished(request.getMatchId())) {
                Question nextQuestion = matchService.getNextQuestionForUser(request.getUserId(), request.getMatchId());
                if (nextQuestion != null) {
                    String questionJson = objectMapper.writeValueAsString(Map.of(
                            "question", nextQuestion.getText(),
                            "id", nextQuestion.getId(),
                            "timeLimit", nextQuestion.getTimeLimit()
                    ));
                    WebSocketSession session = sessions.get(request.getUserId());
                    if (session != null && session.isOpen()) {
                        session.sendMessage(new TextMessage(questionJson));
                    }
                }
            } else {
                sendMatchResult(request.getMatchId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMatchResult(Long matchId) throws IOException {
        Match match = matchService.findById(matchId)
                .orElseThrow(() -> new RuntimeException("Матч не найден"));
        String resultJson = objectMapper.writeValueAsString(Map.of("type", "match_result", "match", match));
        for (Long userId : matchService.getPlayersInMatch(matchId)) {
            WebSocketSession session = sessions.get(userId);
            if (session != null && session.isOpen()) {
                session.sendMessage(new TextMessage(resultJson));
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Можно расширить, если нужно передать начальные данные
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) throws Exception {
        sessions.values().removeIf(s -> s.getId().equals(session.getId()));
    }
}