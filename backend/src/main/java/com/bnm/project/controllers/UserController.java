package com.bnm.project.controllers;

import com.bnm.project.models.GameSessionEntity;
import com.bnm.project.models.UserEntity;
import com.bnm.project.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.PatchExchange;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<UserEntity> addUser(@RequestBody UserEntity userEntity) {
        if(userEntity.getId() == null || userEntity.getId().isBlank()) {
            userEntity.setId(UUID.randomUUID().toString());
        }
        return ResponseEntity.ok(userRepository.save(userEntity));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") String userId) {
        return userRepository.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchExchange ("/{id}/games")
    public ResponseEntity<UserEntity> addGame(@PathVariable("id") String userId, @RequestBody GameSessionEntity gameSessionEntity) {
        Optional<UserEntity> optUser = userRepository.findById(userId);
        if(optUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UserEntity userEntity = optUser.get();
        userEntity.addGameSession(gameSessionEntity);
        return ResponseEntity.ok(userRepository.save(userEntity));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserEntity> getUserByEmail(@PathVariable String email) {
        return userRepository.findUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
