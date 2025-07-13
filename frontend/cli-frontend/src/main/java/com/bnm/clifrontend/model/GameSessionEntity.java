package com.bnm.clifrontend.model;

import java.time.LocalDateTime;
import java.util.List;

public record GameSessionEntity(String id, UserEntity hostUser, CourtEntity court,
                                List<UserEntity> players,
                                LocalDateTime startTime, LocalDateTime endTime) {
    @Override
    public String toString() {
        return "GameSession{" +
                "id='" + id + '\'' +
                ", host=" + hostUser.name() +
                ", court=" + court.name() +
                ", players=" + players.stream().map(UserEntity::name).toList() +
                ", start=" + startTime.toString() +
                ", end=" + endTime.toString() +
                "}\n";
    }
}
