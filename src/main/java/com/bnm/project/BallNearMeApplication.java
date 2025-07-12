package com.bnm.project;

import com.bnm.project.model.User;
import com.bnm.project.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableNeo4jRepositories
public class BallNearMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BallNearMeApplication.class, args);
    }
}
