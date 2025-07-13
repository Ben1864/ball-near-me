package com.bnm.project.repositorys;

import com.bnm.project.models.GameSessionEntity;
import com.bnm.project.models.UserEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSessionRepository extends Neo4jRepository<GameSessionEntity, String> {
    @Query("""
            MATCH (gs: GameSession {id: $sessionId} )-[:HOSTED_BY]->(u:User)
            RETURN u.id AS id, u.name AS name, u.latitude AS latitude, u.longitude AS longitude, u.password AS password,
            u.email AS email
            """)
    UserEntity getGameSessionHostedBy(@Param("sessionId") String id);
}
