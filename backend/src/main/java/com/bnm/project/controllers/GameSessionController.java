package com.bnm.project.controllers;

import com.bnm.project.dtos.GameSessionRequest;
import com.bnm.project.models.CourtEntity;
import com.bnm.project.models.GameSessionEntity;
import com.bnm.project.models.UserEntity;
import com.bnm.project.repositorys.CourtRepository;
import com.bnm.project.repositorys.GameSessionRepository;
import com.bnm.project.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        Optional<UserEntity> hostUser = userRepository.findById(req.hostId);
        Optional<CourtEntity> court = courtRepository.findById(req.courtId);

        if (hostUser.isEmpty() || court.isEmpty()) {
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
    public ResponseEntity<GameSessionEntity> addPlayer(@PathVariable String id, @RequestBody UserEntity user) {
        return gameSessionRepository.findById(id).map(gameSessionEntity -> {
            gameSessionEntity.addPlayer(user);
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
