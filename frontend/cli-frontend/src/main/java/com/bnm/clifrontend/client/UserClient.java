package com.bnm.clifrontend.client;

import com.bnm.clifrontend.model.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PutExchange;

public interface UserClient {

    @PutExchange("/api/users")
    UserEntity createUser(@RequestBody UserEntity user);
}
