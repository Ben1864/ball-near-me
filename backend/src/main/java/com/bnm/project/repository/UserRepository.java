package com.bnm.project.repository;


import com.bnm.project.model.UserEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends Neo4jRepository<UserEntity, String> {

    @Query("""
            MATCH (u:User {email: $email})
            RETURN u
            """)
    Optional<UserEntity> findUserByEmail(@Param("email") String email);
}
