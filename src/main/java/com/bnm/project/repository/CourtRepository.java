package com.bnm.project.repository;

import com.bnm.project.model.CourtEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CourtRepository extends Neo4jRepository<CourtEntity, String> {

}
