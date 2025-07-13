package com.bnm.clifrontend.clients;

import com.bnm.clifrontend.models.GameSessionEntity;
import com.bnm.clifrontend.models.GameSessionRequest;
import com.bnm.clifrontend.models.UserEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface GameSessionClient {

    @PostExchange("/api/games")
    GameSessionEntity addGameSession(@RequestBody GameSessionRequest req);

    @DeleteExchange("/api/games/{id}")
    void deleteGameSession(@PathVariable String id);

    @PatchExchange("/api/games/{id}")
    GameSessionEntity joinGameSession(@PathVariable String id, @RequestBody UserEntity newUser);

    @GetExchange("/api/games")
    List<GameSessionEntity> getAllGames();

    @GetExchange("/api/games/{id}")
    GameSessionEntity getGameSession(@PathVariable String id);
}
