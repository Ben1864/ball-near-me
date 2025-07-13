package com.bnm.project.controllers;

import com.bnm.project.models.CourtEntity;
import com.bnm.project.repositorys.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courts")
public class CourtController {

    @Autowired
    private CourtRepository courtRepository;

    @PostMapping
    public ResponseEntity<CourtEntity> addCourt(@RequestBody CourtEntity courtEntity) {
        if(courtEntity.getId() == null || courtEntity.getId().isBlank()) {
            courtEntity.setId(UUID.randomUUID().toString());
        }
        return ResponseEntity.ok(courtRepository.save(courtEntity));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourtEntity> getCourtById(@PathVariable String id) {
        return courtRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nearby")
    public List<CourtEntity> getNearbyCourts(@RequestParam double lat, @RequestParam double lng,
                                       @RequestParam(defaultValue = "10") int limit) {
        return courtRepository.findClosestCourts(lat, lng, limit);
    }

    @GetMapping
    public ResponseEntity<List<CourtEntity>> getAllCourts() {
        return ResponseEntity.ok(courtRepository.findAll());
    }
}
