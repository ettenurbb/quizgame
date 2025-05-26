package com.quizgame.quizgame.controller;

import com.quizgame.quizgame.model.Match;
import com.quizgame.quizgame.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping("/start")
    public Match startMatch(@RequestParam Long player1Id, @RequestParam Long player2Id) {
        return matchService.startMatch(player1Id, player2Id);
    }

    @PostMapping("/finish")
    public void finishMatch(@RequestParam Long matchId) {
        matchService.finishMatch(matchId);
    }
}