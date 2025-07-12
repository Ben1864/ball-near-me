package com.bnm.project.controller;

import com.bnm.project.model.UserEntity;
import com.bnm.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable String id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
