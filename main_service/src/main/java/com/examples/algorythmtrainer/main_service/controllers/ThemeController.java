package com.examples.algorythmtrainer.main_service.controllers;

import com.examples.algorythmtrainer.main_service.dto.ThemeResponse;
import com.examples.algorythmtrainer.main_service.services.ThemeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main/themes")
@Tag(name = "Темы", description = "Работа с темами иерархии задач")
public class ThemeController {

    private ThemeService themeService;

    Logger log = LoggerFactory.getLogger(ThemeController.class);

    @Autowired
    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @Operation(summary = "Получить список тем")
    @GetMapping("/")
    public ResponseEntity<List<ThemeResponse>> getThemes() {
        log.info("Get themes");
        return ResponseEntity.ok(themeService.getThemes());
    }
}
