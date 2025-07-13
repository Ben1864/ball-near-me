package com.bnm.clifrontend.commands;

import org.springframework.stereotype.Component;

@Component
public class AuthState {
    private boolean userLoggedIn = false;
    private String userId = null;

    public AuthState() {}

    public AuthState(boolean loggedInUser, String userId) {}

    public boolean isUserLoggedIn() {
        return userLoggedIn;
    }
    public String getUserId() {
        return userId;
    }
    public void login(String userId) {
        this.userLoggedIn = true;
        this.userId = userId;
    }
    public void logout() {
        this.userLoggedIn = false;
        this.userId = null;
    }
}
