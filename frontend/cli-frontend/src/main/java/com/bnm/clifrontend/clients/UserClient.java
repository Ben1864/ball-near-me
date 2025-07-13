package com.bnm.clifrontend.clients;

import com.bnm.clifrontend.models.GameSessionEntity;
import com.bnm.clifrontend.models.UserEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.service.annotation.PostExchange;

public interface UserClient {

    @PostExchange ("/api/users")
    UserEntity createUser(@RequestBody UserEntity user);

    @GetExchange("/api/users/email/{email}")
    UserEntity getUserByEmail(@PathVariable("email") String email);

    @GetExchange("/api/users/id/{id}")
    UserEntity getUserById(@PathVariable("id") String id);

    @PatchExchange("/api/users/{id}/games")
    void addGame(@PathVariable("id") String id, @RequestBody GameSessionEntity game);
}
