package com.bnm.project.controllers;

import com.bnm.project.model.CourtEntity;
import com.bnm.project.repository.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
