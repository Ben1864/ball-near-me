package com.bnm.clifrontend.commands;

import com.bnm.clifrontend.client.GeocodingClient;
import com.bnm.clifrontend.client.UserClient;
import com.bnm.clifrontend.model.GoogleGeocodingResponse;
import com.bnm.clifrontend.model.UserEntity;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class UserCommands {

    private final AuthState authState;
    private final UserClient userClient;
    private final Terminal terminal;
    private final GeocodingClient geocodingClient;
    private final String apiKey;

    public UserCommands(AuthState authState, UserClient userClient, Terminal terminal, GeocodingClient geocodingClient, String apiKey) {
        this.authState = authState;
        this.terminal = terminal;
        this.userClient = userClient;
        this.geocodingClient = geocodingClient;
        this.apiKey = apiKey;
    }

    @ShellMethod(key = "signup", value = "create user node")
    public String createUser() {
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();

        String name = reader.readLine("Enter your name: ");
        String password = reader.readLine("Enter your password: ");
        String email = reader.readLine("Enter your email: ");
        String location = reader.readLine("Enter your location: ");

        GoogleGeocodingResponse response = geocodingClient.geocode(location, apiKey);
        double latitude = response.results().get(0).geometry().location().lat();
        double longitude = response.results().get(0).geometry().location().lng();

        UserEntity createdUser = userClient.createUser(new UserEntity(null, name, password, email, latitude, longitude));
        return createdUser.toString();
    }

    @ShellMethod(key = "login", value = "Login user based on email")
    public String login() {
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();
        String email = reader.readLine("Enter your email: ");
        String password = reader.readLine("Enter your password: ");

        UserEntity loggedInUser = userClient.getUserByEmail(email);
        if  (loggedInUser == null) {
            return "Invalid email, try signing up";
        } else if (!loggedInUser.password().equals(password)) {
            return "Invalid password, try login again";
        }
        authState.login(loggedInUser.id());

        return loggedInUser.toString();
    }

    @ShellMethod(key = "logout", value = "logout user (only available if logged in)")
    public String logout() {
        authState.logout();
        return "User logged out";
    }
}
