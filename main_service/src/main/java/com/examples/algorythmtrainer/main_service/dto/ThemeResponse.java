package com.examples.algorythmtrainer.main_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация о теме задач")
public class ThemeResponse {

    @JsonProperty("theme_id")
    @Schema(description = "ID темы", example = "2")
    Integer themeId;

    @Schema(description = "Название темы", example = "Сортировки")
    String title;

    @JsonProperty("parent_theme_id")
    @Schema(description = "ID родительской темы", example = "1", nullable = true)
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
