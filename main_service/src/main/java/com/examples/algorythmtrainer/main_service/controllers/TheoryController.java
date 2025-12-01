package com.examples.algorythmtrainer.main_service.controllers;

import com.examples.algorythmtrainer.main_service.dto.CommentRequest;
import com.examples.algorythmtrainer.main_service.dto.CommentResponse;
import com.examples.algorythmtrainer.main_service.dto.TheoryResponse;
import com.examples.algorythmtrainer.main_service.services.AlgorythmTheoryService;
import com.examples.algorythmtrainer.main_service.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main/theory")
@Tag(name = "Теория", description = "Работа с теоретическими материалами и комментариями к ним")
public class TheoryController {

    private final AlgorythmTheoryService algorythmTheoryService;
    private final CommentService commentService;

    Logger log = LoggerFactory.getLogger(TheoryController.class);

    @Autowired
    public TheoryController(AlgorythmTheoryService algorythmTheoryService, CommentService commentService) {
        this.algorythmTheoryService = algorythmTheoryService;
        this.commentService = commentService;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Теория найдена"),
            @ApiResponse(responseCode = "404", description = "Теория не найдена")
    })
    @Operation(
            summary = "Получить теорию по ID",
            description = "Возвращает теоретический материал по идентификатору."
    )
    @GetMapping("/{id}")
    public ResponseEntity<TheoryResponse> getTheoryById(
            @Parameter( description = "ID теории", required = true) @PathVariable int id) {
        log.info("Get Theory By Id: {}", id);
        TheoryResponse response = algorythmTheoryService.getAlgorythmTheory(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Получить комментарии к теории",
            description = "Возвращает список комментариев к теоретическому материалу."
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponse>> getTheoryComments(
            @Parameter( description = "ID теории", required = true) @PathVariable int id) {
        log.info("Get Theory Comments By Id: {}", id);
        return ResponseEntity.ok(commentService.getTheoryComments(id));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Комментарий добавлен"),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован")
    })
    @Operation(
            summary = "Добавить комментарий к теории",
            description = "Добавляет новый комментарий к теоретическому материалу."
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponse> addTheoryComment(
            @Parameter( description = "ID теории", required = true) @PathVariable int id,
            @RequestBody CommentRequest commentRequest) {
        log.info("Add Theory Comment By Id: {}", id);
        CommentResponse saved = commentService.addTheoryComment(commentRequest, id);
        return ResponseEntity.ok(saved);
    }
}
