package com.bnm.project.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("User")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String name;

    public User(){

    };
    public User(String name) {
        this.name = name;
    }
}
