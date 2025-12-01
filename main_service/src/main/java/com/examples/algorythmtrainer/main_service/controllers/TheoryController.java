package com.examples.algorythmtrainer.main_service.controllers;


import com.examples.algorythmtrainer.main_service.dto.CommentRequest;
import com.examples.algorythmtrainer.main_service.dto.CommentResponse;
import com.examples.algorythmtrainer.main_service.dto.TheoryResponse;
import com.examples.algorythmtrainer.main_service.models.AlgorythmTheory;
import com.examples.algorythmtrainer.main_service.models.TheoryComment;
import com.examples.algorythmtrainer.main_service.repositories.AlgorythmTheoryRepository;
import com.examples.algorythmtrainer.main_service.services.AlgorythmTheoryService;
import com.examples.algorythmtrainer.main_service.services.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/main/theory")
public class TheoryController {

    private AlgorythmTheoryService algorythmTheoryService;
    private CommentService commentService;

    @Autowired
    public TheoryController(AlgorythmTheoryService algorythmTheoryService, CommentService commentService) {
        this.algorythmTheoryService = algorythmTheoryService;
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheoryResponse> getTheoryById(@PathVariable int id) {
        log.info("Get Theory By Id");
        TheoryResponse response = algorythmTheoryService.getAlgorythmTheory(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponse>> getTheoryComments(@PathVariable int id) {
        log.info("Get Theory Comments By Id");
        return ResponseEntity.ok(commentService.getTheoryComments(id));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponse> addTheoryComment(@PathVariable int id, @RequestBody CommentRequest commentRequest) {
        log.info("Add Theory Comment By Id");
        CommentResponse saved = commentService.addTheoryComment(commentRequest, id);
        return ResponseEntity.ok(saved);
    }

}
