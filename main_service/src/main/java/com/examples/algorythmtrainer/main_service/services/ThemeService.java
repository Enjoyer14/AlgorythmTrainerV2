package com.examples.algorythmtrainer.main_service.services;

import com.examples.algorythmtrainer.main_service.dto.TasksResponse;
import com.examples.algorythmtrainer.main_service.dto.ThemeResponse;
import com.examples.algorythmtrainer.main_service.models.Task;
import com.examples.algorythmtrainer.main_service.models.Theme;
import com.examples.algorythmtrainer.main_service.repositories.ThemeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThemeService {
    private ThemeRepository themeRepository;

    Logger log = LoggerFactory.getLogger(ThemeService.class);

    @Autowired
    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public List<ThemeResponse> getThemes() {
        log.info("Get themes");
        List<Theme> themes = themeRepository.findAll();
        return themes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    private ThemeResponse toDto(Theme theme) {
        return new ThemeResponse(
                theme.getThemeId(),
                theme.getTitle(),
                theme.getParentTheme().getThemeId()
        );
    }

}
