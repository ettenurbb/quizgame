package com.quizgame.quizgame.controller;

import com.quizgame.quizgame.dto.AnswerSubmissionRequest;
import com.quizgame.quizgame.model.Match;
import com.quizgame.quizgame.model.Question;
import com.quizgame.quizgame.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/start")
    public Match startMatch(@RequestParam Long player1Id, @RequestParam Long player2Id) {
        return matchService.startMatch(player1Id, player2Id);
    }

    @PostMapping("/submit-answer")
    public void submitAnswer(@RequestBody AnswerSubmissionRequest request) {
        matchService.processAnswer(request);
    }

    @GetMapping("/next-question")
    public Question getNextQuestion(@RequestParam Long userId) {
        return matchService.getNextQuestionForUser(userId);
    }

    @GetMapping("/status")
    public Map<String, Object> getMatchStatus(@RequestParam Long matchId) {
        Match match = matchService.getMatchById(matchId);
        boolean finished = matchService.isMatchFinished(matchId);

        return Map.of(
                "match", match,
                "finished", finished
        );
    }

    @PostMapping("/finish")
    public void finishMatch(@RequestParam Long matchId) {
        matchService.finishMatch(matchId);
    }
}