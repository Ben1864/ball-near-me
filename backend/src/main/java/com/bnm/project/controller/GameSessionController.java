package com.bnm.project.controller;

import com.bnm.project.dto.GameSessionRequest;
import com.bnm.project.model.CourtEntity;
import com.bnm.project.model.GameSessionEntity;
import com.bnm.project.model.UserEntity;
import com.bnm.project.repository.CourtRepository;
import com.bnm.project.repository.GameSessionRepository;
import com.bnm.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameSessionController {
    @Autowired
    private GameSessionRepository gameSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourtRepository courtRepository;

    @GetMapping("/{id}")
    public ResponseEntity<GameSessionEntity> getGameSessionById(@PathVariable String id) {
        return  gameSessionRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GameSessionEntity> addGameSession(@RequestBody GameSessionRequest req) {
        Optional<UserEntity> hostUser = userRepository.findById(req.hostUserId);
        Optional<CourtEntity> court = courtRepository.findById(req.courtId);


        if (hostUser.isEmpty() && court.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        GameSessionEntity gameSessionEntity = new GameSessionEntity(
                hostUser.get(),
                court.get(),
                req.startTime,
                req.endTime
        );
        return  ResponseEntity.ok(gameSessionRepository.save(gameSessionEntity));
    }

    @GetMapping
    public ResponseEntity<List<GameSessionEntity>> getAllGameSessions() {
        return ResponseEntity.ok(gameSessionRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void deleteGameSessionById(@RequestBody GameSessionEntity gameSessionEntity) {
        gameSessionRepository.delete(gameSessionEntity);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GameSessionEntity> addPlayer(@PathVariable String id, @RequestBody UserEntity newPlayer) {
        return gameSessionRepository.findById(id).map(gameSessionEntity -> {
            gameSessionEntity.addPlayer(newPlayer);
            return ResponseEntity.ok(gameSessionRepository.save(gameSessionEntity));
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/host")
    public ResponseEntity<UserEntity> getGameSessionHost(@PathVariable String id) {
        UserEntity hostUser = gameSessionRepository.getGameSessionHostedBy(id);
        System.out.println(hostUser);
        if (hostUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hostUser);
    }
}
