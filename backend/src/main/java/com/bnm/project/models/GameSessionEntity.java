package com.bnm.project.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Node("GameSession")
public class GameSessionEntity {
    @Id
    @Getter
    @Setter
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    @Getter
    @Setter
    @Relationship(type = "HOSTED_BY", direction = Relationship.Direction.OUTGOING)
    private UserEntity hostUser;

    @Getter
    @Setter
    @Relationship(type = "PLAYING_IN", direction = Relationship.Direction.INCOMING)
    private List<UserEntity> players = new ArrayList<>();

    @Getter
    @Setter
    @Relationship(type = "HOSTED_AT", direction = Relationship.Direction.OUTGOING)
    private CourtEntity court;

    @Getter
    @Setter
    private LocalDateTime startTime;

    @Getter
    @Setter
    private LocalDateTime endTime;

    public GameSessionEntity() {}

    public GameSessionEntity(UserEntity hostUser, CourtEntity court) {
        this.hostUser = hostUser;
        this.court = court;
        startTime = LocalDateTime.now();
        endTime = LocalDateTime.now().plusHours(1);
    }

    public GameSessionEntity(UserEntity hostUser, CourtEntity court, LocalDateTime startTime) {
        this.hostUser = hostUser;
        this.court = court;
        this.startTime = startTime;
        this.endTime = startTime.plusHours(1);
    }

    public GameSessionEntity(UserEntity hostUser, CourtEntity court, LocalDateTime startTime, LocalDateTime endTime) {
        this.hostUser = hostUser;
        this.court = court;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void addPlayer(UserEntity newPlayer){
        this.players.add(newPlayer);
    }

}
