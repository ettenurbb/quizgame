package com.quizgame.quizgame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSubmissionRequest {
    private Long userId;
    private Long matchId;
    private Long questionId;
    private Long selectedAnswerId;
    private Double timeTaken;
}