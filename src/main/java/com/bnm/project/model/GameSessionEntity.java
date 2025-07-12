package com.bnm.project.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("GameSession")
public class GameSessionEntity {
    @Id
    @Getter
    @Setter
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    @Getter
    @Setter
    private String hostUserId;

    @Getter
    @Setter
    private String courtId;

    public GameSessionEntity() {}

    public GameSessionEntity(String hostUserId, String courtId) {
        this.hostUserId = hostUserId;
        this.courtId = courtId;
    }

}
