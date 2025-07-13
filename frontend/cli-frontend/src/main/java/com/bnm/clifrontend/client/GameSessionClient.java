package com.bnm.clifrontend.client;

import com.bnm.clifrontend.model.GameSessionEntity;
import com.bnm.clifrontend.model.GameSessionRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface GameSessionClient {

    @PostMapping("/api/games")
    GameSessionEntity addGameSession(@RequestBody GameSessionRequest req);
}
