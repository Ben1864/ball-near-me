package com.bnm.project.repository;

import com.bnm.project.model.CourtEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourtRepository extends Neo4jRepository<CourtEntity, String> {

    @Query("""
            MATCH (c:Court)
            WITH c, point({latitude: $lat, longitude: $lng}) AS userLocation,
                point({latitude: c.latitude, longitude: c.latitude}) AS courtLocation
            RETURN c
            ORDER BY point.distance(userLocation, courtLocation) ASC
            LIMIT $limit
            """)
    List<CourtEntity> findClosestCourts(@Param("lat") double lat, @Param("lng") double lng, @Param("limit") int limit);
}
