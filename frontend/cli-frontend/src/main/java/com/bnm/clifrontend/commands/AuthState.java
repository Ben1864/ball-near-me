package com.bnm.clifrontend.commands;

import org.springframework.shell.Availability;
import org.springframework.stereotype.Component;

@Component
public class AuthState {
    private boolean userLoggedIn = false;
    private String userId = null;

    public AuthState() {}

    public AuthState(boolean loggedInUser, String userId) {}

    public Availability isUserLoggedIn() {
        return userLoggedIn ? Availability.available() : Availability.unavailable("User is not logged in");
    }
    public String getUserId() {
        return userId;
    }

    public boolean isLoggedIn() {
        return userLoggedIn;
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
