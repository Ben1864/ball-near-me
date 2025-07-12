package com.bnm.project.repository;

import com.bnm.project.model.GameSessionEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface GameSessionRepository extends Neo4jRepository<GameSessionEntity, String> {
}
