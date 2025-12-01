package com.examples.algorythmtrainer.main_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Краткая информация о задаче для списка")
public class TasksResponse {

    @JsonProperty("task_id")
    @Schema(description = "ID задачи", example = "5")
    private Integer taskId;

    @Schema(description = "Название задачи", example = "Сумма чисел")
    private String title;

    @JsonProperty("difficulty_level")
    @Schema(description = "Уровень сложности задачи", example = "EASY")
    private String difficultyLevel;

    @JsonProperty("theme_id")
    @Schema(description = "ID темы, к которой относится задача", example = "2")
    private Integer themeId;

    @JsonProperty("theme_title")
    @Schema(description = "Название темы", example = "Поиск")
    private String themeTitle;

    public TasksResponse() {
    }

    public TasksResponse(Integer taskId, String title,
                         String difficultyLevel, Integer themeId, String themeTitle) {
        this.taskId = taskId;
        this.title = title;
        this.difficultyLevel = difficultyLevel;
        this.themeId = themeId;
        this.themeTitle = themeTitle;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getThemeTitle() {
        return themeTitle;
    }

    public void setThemeTitle(String themeTitle) {
        this.themeTitle = themeTitle;
    }
}
