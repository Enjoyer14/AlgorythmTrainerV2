package com.examples.algorythmtrainer.main_service.repositories;

import com.examples.algorythmtrainer.main_service.models.Task;
import com.examples.algorythmtrainer.main_service.models.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository  extends JpaRepository<Theme, Integer> {
    Theme findThemeByThemeId(Integer id);
}
