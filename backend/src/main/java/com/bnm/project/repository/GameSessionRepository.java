package com.bnm.project.repository;

import com.bnm.project.model.GameSessionEntity;
import com.bnm.project.model.UserEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface GameSessionRepository extends Neo4jRepository<GameSessionEntity, String> {
    @Query("""
            MATCH (gs: GameSession {id: $sessionId} )-[:HOSTED_BY]->(u:User)
            RETURN u.id AS id, u.name AS name, u.latitude AS latitude, u.longitude AS longitude, u.password AS password,
            u.email AS email
            """)
    UserEntity getGameSessionHostedBy(@Param("sessionId") String id);
}
