package com.bnm.clifrontend.commands;

import com.bnm.clifrontend.client.CourtClient;
import com.bnm.clifrontend.client.GameSessionClient;
import com.bnm.clifrontend.client.UserClient;
import com.bnm.clifrontend.model.GameSessionEntity;
import com.bnm.clifrontend.model.GameSessionRequest;
import com.bnm.clifrontend.model.UserEntity;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @ShellMethod(key = "add-game-session", value = "Add new game session")
    @ShellMethodAvailability("isUserLoggedIn")
    public String addGameSession(@ShellOption(help="Court ID used to host the game") String courtId) {
        String userId = authState.getUserId();
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();

        System.out.println("Leave empty to set at now");
        String startTimeInput = reader.readLine("Enter start date/time (HH:MM DD-MM-YYYY): ");
        System.out.println("Leave empty to set as in 1 hour from now");
        String endTimeInput = reader.readLine("Enter end date/time (HH:MM DD-MM-YYYY): ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy ");

        LocalDateTime startTime;
        if (startTimeInput == null || startTimeInput.isBlank()) {
            startTime = LocalDateTime.now();
        } else {
            startTime = LocalDateTime.parse(startTimeInput, formatter);
        }

        LocalDateTime endTime;
        if (endTimeInput == null || endTimeInput.isBlank()) {
            endTime = LocalDateTime.now().plusHours(1);
        } else {
            endTime = LocalDateTime.parse(endTimeInput, formatter);
        }
        GameSessionRequest gameSessionReq = new GameSessionRequest(userId, courtId, startTime, endTime);
        GameSessionEntity newGameSession = gameSessionClient.addGameSession(gameSessionReq);
        return newGameSession.toString();
    }

    @ShellMethod(key = "show-all-games", value = "Show all Game Sessions")
    public String showAllGames() {
        List<GameSessionEntity> gameSessionEntities = gameSessionClient.getAllGames();
        StringBuilder sb = new StringBuilder();
        for (GameSessionEntity gameSessionEntity : gameSessionEntities) {
            sb.append(gameSessionEntity.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    @ShellMethod(key = "delete-game", value = "Delete a game session")
    @ShellMethodAvailability("isUserLoggedIn")
    public String deleteGameSession(@ShellOption(help="Game Session ID to be deleted")String id) {
        gameSessionClient.deleteGameSession(id);
        return "Deleted game session with id: " + id;
    }

    @ShellMethod(key = "join-game", value = "Join a game")
    @ShellMethodAvailability("isUserLoggedIn")
    public String joinGameSession(@ShellOption(help="Game Session ID the user wants to join") String id) {
        UserEntity currentUser = userClient.getUserById(authState.getUserId());

        gameSessionClient.joinGameSession(id, currentUser);

        GameSessionEntity game = gameSessionClient.getGameSession(id);
        userClient.addGame(authState.getUserId(), game);
        return "You successfully joined game session with id: " + id + "!";
    }

    public Availability isUserLoggedIn() {
        return authState.isUserLoggedIn();
    }

}
