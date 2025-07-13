package com.bnm.clifrontend.commands;

import com.bnm.clifrontend.client.CourtClient;
import com.bnm.clifrontend.model.CourtEntity;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.List;

@ShellComponent
public class CourtCommands {
    private final CourtClient courtClient;

    public CourtCommands(CourtClient courtClient) {
        this.courtClient = courtClient;
    }

    @ShellMethod(key = "all courts", value = "I will print all courts")
    public String  allCourts() {
        List<CourtEntity> courts = courtClient.getCourts();
        StringBuilder sb = new StringBuilder();
        for (CourtEntity court : courts) {
            sb.append(court.toString());
        }
        return sb.toString();
    }
}
