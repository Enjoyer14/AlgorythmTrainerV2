package com.examples.algorythmtrainer.main_service.controllers;


import com.examples.algorythmtrainer.main_service.dto.TheoryResponse;
import com.examples.algorythmtrainer.main_service.models.AlgorythmTheory;
import com.examples.algorythmtrainer.main_service.repositories.AlgorythmTheoryRepository;
import com.examples.algorythmtrainer.main_service.services.AlgorythmTheoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/theory")
public class TheoryController {

    AlgorythmTheoryService algorythmTheoryService;

    @Autowired
    public TheoryController(AlgorythmTheoryService algorythmTheoryService) {
        this.algorythmTheoryService = algorythmTheoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheoryResponse> getTheoryById(@PathVariable int id) {
        TheoryResponse response = algorythmTheoryService.getAlgorythmTheory(id);
        return ResponseEntity.ok(response);
    }




}
