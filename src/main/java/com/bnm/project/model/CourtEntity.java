package com.bnm.project.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("Court")
public class CourtEntity {
    @Id
    @Getter
    @Setter
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String location;

    public CourtEntity() {}

    public CourtEntity(String name, String location) {
        this.name = name;
        this.location = location;
    }
}
