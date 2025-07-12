package com.bnm.project.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("User")
public class UserEntity {
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
    private String password;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private double latitude;

    @Getter
    @Setter
    private double longitude;

    public UserEntity(){

    }
    public UserEntity(String name, String password, String email, double latitude, double longitude) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
