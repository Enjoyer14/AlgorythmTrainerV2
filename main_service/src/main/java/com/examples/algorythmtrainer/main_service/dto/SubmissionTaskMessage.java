package com.examples.algorythmtrainer.main_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubmissionTaskMessage {

    @JsonProperty("submission_id")
    private Integer submissionId;

    @JsonProperty("task_id")
    private Integer taskId;

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("code")
    private String code;

    @JsonProperty("language")
    private String language;

    public SubmissionTaskMessage() {}

    public SubmissionTaskMessage(Integer submissionId, Integer taskId, Integer userId, String code, String language) {
        this.submissionId = submissionId;
        this. taskId = taskId;
        this.userId = userId;
        this.code = code;
        this.language = language;
    }

    public Integer getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Integer submissionId) {
        this.submissionId = submissionId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}