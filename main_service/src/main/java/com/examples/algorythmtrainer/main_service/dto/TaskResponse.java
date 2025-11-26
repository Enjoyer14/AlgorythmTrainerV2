package com.examples.algorythmtrainer.main_service.dto;

import com.examples.algorythmtrainer.main_service.models.TaskTestCase;

import java.util.List;

public class TaskResponse {
    private Integer taskId;
    private String title;
    private String description;
    private String difficultyLevel;
    private Integer timeLimitMs;
    private Integer memoryLimitMb;
    private List<TaskTestCaseExamplesResponse> taskTestCases;


    public TaskResponse() {
    }

    public TaskResponse(Integer taskId, String title, String description,
                         String difficultyLevel, Integer timeLimitMs, Integer memoryLimitMb,
                        List<TaskTestCaseExamplesResponse> taskTestCases) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.timeLimitMs = timeLimitMs;
        this.memoryLimitMb = memoryLimitMb;
        this.taskTestCases = taskTestCases;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getTimeLimitMs() {
        return timeLimitMs;
    }

    public void setTimeLimitMs(Integer timeLimitMs) {
        this.timeLimitMs = timeLimitMs;
    }

    public Integer getMemoryLimitMb() {
        return memoryLimitMb;
    }

    public void setMemoryLimitMb(Integer memoryLimitMb) {
        this.memoryLimitMb = memoryLimitMb;
    }

    public List<TaskTestCaseExamplesResponse> getTaskTestCases() {
        return taskTestCases;
    }

    public void setTaskTestCases(List<TaskTestCaseExamplesResponse> taskTestCases) {
        this.taskTestCases = taskTestCases;
    }
}
