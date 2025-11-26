package com.examples.algorythmtrainer.main_service.dto;

public class TasksResponse {

    private Integer taskId;
    private String title;
    private String difficultyLevel;
    private Integer themeId;
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
