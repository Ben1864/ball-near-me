package com.bnm.clifrontend.commands;

import com.bnm.clifrontend.client.CourtClient;
import com.bnm.clifrontend.client.GeocodingClient;
import com.bnm.clifrontend.client.UserClient;
import com.bnm.clifrontend.model.CourtEntity;
import com.bnm.clifrontend.model.GoogleGeocodingResponse;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@ShellComponent
public class CourtCommands {
    private final CourtClient courtClient;
    private final Terminal terminal;
    private final GeocodingClient geocodingClient;
    private final String apiKey;


    public CourtCommands(CourtClient courtClient, Terminal terminal, GeocodingClient geocodingClient, String apiKey) {
        this.courtClient = courtClient;
        this.terminal = terminal;
        this.geocodingClient = geocodingClient;
        this.apiKey = apiKey;
    }

    @ShellMethod(key = "show-all-courts", value = "Show all courts")
    public String  allCourts() {
        List<CourtEntity> allCourts = courtClient.getCourts();
        StringBuilder sb = new StringBuilder();
        for (CourtEntity court : allCourts) {
            sb.append(court.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    @ShellMethod(key = "add-court", value = "Add a new court")
    public String  addCourt() {
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();

        String name = reader.readLine("Enter court name: ");
        String location = reader.readLine("Enter court location: ");

        GoogleGeocodingResponse response = geocodingClient.geocode(location, apiKey);
        double latitude = response.results().get(0).geometry().location().lat();
        double longitude = response.results().get(0).geometry().location().lng();

        CourtEntity newCourt = courtClient.addCourt(new CourtEntity(null, name, latitude, longitude));
        return  newCourt.toString();
    }

    @ShellMethod(key = "get-court", value = "Get court by court id")
    public String  getCourtById(@PathVariable("id") String id) {
        CourtEntity court = courtClient.getCourtById(id);
        return court.toString();
    }

    @ShellMethod(key = "get-nearby-courts", value = "Get n closest courts to your location")
    public String  getNearbyCourts(@ShellOption(help = "limit on closest court responses", defaultValue = "10" )int n) {
        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();
        String location = reader.readLine("Enter your current location: ");

        GoogleGeocodingResponse response = geocodingClient.geocode(location, apiKey);
        double latitude = response.results().get(0).geometry().location().lat();
        double longitude = response.results().get(0).geometry().location().lng();

        List<CourtEntity> closestCourts = courtClient.getNearbyCourts(latitude, longitude, n);
        StringBuilder sb = new StringBuilder();
        for (CourtEntity court : closestCourts) {
            sb.append(court.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}
