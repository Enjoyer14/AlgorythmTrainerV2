package com.examples.algorythmtrainer.main_service.dto;

import com.examples.algorythmtrainer.main_service.models.Theme;

public class ThemeResponse {
    Integer themeId;
    String title;
    Integer parentThemeId;

    public ThemeResponse() {
    }

    public ThemeResponse(Integer themeId, String title, Integer parentThemeId) {
        this.themeId = themeId;
        this.title = title;
        this.parentThemeId = parentThemeId;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getParentThemeId() {
        return parentThemeId;
    }

    public void setParentThemeId(Integer parentThemeId) {
        this.parentThemeId = parentThemeId;
    }
}
