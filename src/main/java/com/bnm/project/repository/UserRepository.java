package com.bnm.project.repository;


import com.bnm.project.model.UserEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends Neo4jRepository<UserEntity, String> {
}
