package com.bnm.project.controller;

import com.bnm.project.repository.GameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
public class GameSessionController {
    @Autowired
    private GameSessionRepository gameSessionRepository;




}
