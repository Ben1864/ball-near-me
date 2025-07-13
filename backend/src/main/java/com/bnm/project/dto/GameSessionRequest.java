package com.bnm.project.dto;

import java.time.LocalDateTime;

public class GameSessionRequest {
    public String hostId;
    public String courtId;
    public LocalDateTime startTime;
    public LocalDateTime endTime;
}
