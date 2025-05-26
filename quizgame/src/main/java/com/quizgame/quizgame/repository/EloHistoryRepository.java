package com.quizgame.quizgame.repository;

import com.quizgame.quizgame.model.EloHistory;
import com.quizgame.quizgame.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EloHistoryRepository extends JpaRepository<EloHistory, Long> {
    List<EloHistory> findByUser(User user);
}