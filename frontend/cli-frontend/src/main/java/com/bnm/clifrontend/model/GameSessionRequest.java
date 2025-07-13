package com.bnm.clifrontend.model;

import java.time.LocalDateTime;

public record GameSessionRequest (String hostId, String courtId, LocalDateTime startTime, LocalDateTime endTime) {
}
