package com.bnm.clifrontend.commands;

import com.bnm.clifrontend.client.CourtClient;
import com.bnm.clifrontend.client.GeocodingClient;
import com.bnm.clifrontend.model.CourtEntity;
import com.bnm.clifrontend.model.GoogleGeocodingResponse;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
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
        List<CourtEntity> courts = courtClient.getCourts();
        StringBuilder sb = new StringBuilder();
        for (CourtEntity court : courts) {
            sb.append(court.toString());
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
}
