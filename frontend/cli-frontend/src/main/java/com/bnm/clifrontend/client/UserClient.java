package com.bnm.clifrontend.client;

import com.bnm.clifrontend.model.UserEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

public interface UserClient {

    @PostExchange ("/api/users")
    UserEntity createUser(@RequestBody UserEntity user);

    @GetExchange("/api/users/email/{email}")
    UserEntity getUserByEmail(@PathVariable("email") String email);
}
