package com.bnm.clifrontend.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public record GameSessionEntity(String id, UserEntity hostUser, CourtEntity court,
                                List<UserEntity> players,
                                LocalDateTime startTime, LocalDateTime endTime) {
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");

        return "GameSession{" +
                "id='" + id + '\'' +
                ", host='" + hostUser.name() + '\'' +
                ", court='" + court.name() + '\'' +
                ", players=" + players.stream().map(UserEntity::name).toList() +
                ", start=" + startTime.format(formatter) +
                ", end=" + endTime.format(formatter) +
                "}";
    }
}
