package com.bnm.clifrontend.model;

import java.time.LocalDateTime;
import java.util.List;

public record GameSessionEntity(String id, UserEntity user, CourtEntity court,
                                List<UserEntity> players,
                                LocalDateTime startTime, LocalDateTime endTime) {
}
