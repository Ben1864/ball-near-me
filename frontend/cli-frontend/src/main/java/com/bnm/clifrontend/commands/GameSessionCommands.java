package com.bnm.clifrontend.commands;

import com.bnm.clifrontend.client.CourtClient;
import com.bnm.clifrontend.client.GameSessionClient;
import com.bnm.clifrontend.client.UserClient;
import com.bnm.clifrontend.model.GameSessionEntity;
import com.bnm.clifrontend.model.GameSessionRequest;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ShellComponent
public class GameSessionCommands {

    private final GameSessionClient gameSessionClient;
    private final CourtClient courtClient;
    private final UserClient userClient;
    private final AuthState authState;
    private final Terminal terminal;

    public GameSessionCommands(GameSessionClient gameSessionClient, CourtClient courtClient,
                               UserClient userClient, AuthState authState, Terminal terminal) {
        this.gameSessionClient = gameSessionClient;
        this.courtClient = courtClient;
        this.userClient = userClient;
        this.authState = authState;
        this.terminal = terminal;
    }

    @ShellMethod(key = "add-game-session")
    @ShellMethodAvailability("isUserLoggedIn")
    public String addGameSession(String courtId) {
        String userId = authState.getUserId();
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();

        System.out.println("Leave empty to set at now");
        String startTimeInput = reader.readLine("Enter start date/time (yyyy-MM-dd HH:mm): ");
        System.out.println("Leave empty to set as in 1 hour from now");
        String endTimeInput = reader.readLine("Enter end date/time (yyyy-MM-dd HH:mm): ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime startTime;
        if (startTimeInput == null || startTimeInput.isBlank()) {
            startTime = LocalDateTime.now();
        } else {
            startTime = LocalDateTime.parse(startTimeInput, formatter);
        }

        LocalDateTime endTime;
        if (endTimeInput == null || endTimeInput.isBlank()) {
            endTime = LocalDateTime.now();
        } else {
            endTime = LocalDateTime.parse(endTimeInput, formatter);
        }
        GameSessionRequest gameSessionReq = new GameSessionRequest(userId, courtId, startTime, endTime);
        GameSessionEntity newGameSession = gameSessionClient.addGameSession(gameSessionReq);
        return newGameSession.toString();
    }

    public Availability isUserLoggedIn() {
        return authState.isUserLoggedIn();
    }

}
