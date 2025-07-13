package com.bnm.clifrontend.commands;

import com.bnm.clifrontend.client.UserClient;
import com.bnm.clifrontend.model.UserEntity;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class UserCommands {

    private final UserClient userClient;

    private final Terminal terminal;

    public UserCommands(UserClient userClient, Terminal terminal) {
        this.terminal = terminal;
        this.userClient = userClient;
    }

    @ShellMethod(key = "signup", value = "create user node")
    public String createUser(UserEntity user) {
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();

        String name = reader.readLine("Enter your name: ");
        String password = reader.readLine("Enter your password: ");
        String email = reader.readLine("Enter your email: ");
        String Location = reader.readLine("Enter your location: ");

        return "";


    }
}
