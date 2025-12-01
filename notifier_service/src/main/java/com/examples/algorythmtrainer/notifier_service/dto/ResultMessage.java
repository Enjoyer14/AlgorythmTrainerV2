package com.examples.algorythmtrainer.notifier_service. dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ResultMessage {

    @JsonProperty("user_id")
    private Integer userId;

    @JsonProperty("submission_id")
    private Integer submissionId;

    @JsonProperty("task_id")
    private Integer taskId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("passed")
    private Boolean passed;

    @JsonProperty("result")
    private String result;

    @JsonProperty("error")
    private String error;

    @JsonProperty("execution_time")
    private Double executionTime;

    @JsonProperty("memory_used")
    private Double memoryUsed;

    public ResultMessage() {}

    public ResultMessage(Integer submissionId, Integer taskId, Integer userId, String status, Boolean passed, String error, String result, Double executionTime, Double memoryUsed) {
        this.submissionId = submissionId;
        this.taskId = taskId;
        this.userId = userId;
        this.status = status;
        this.passed = passed;
        this.error = error;
        this.result = result;
        this.executionTime = executionTime;
        this.memoryUsed = memoryUsed;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Double executionTime) {
        this.executionTime = executionTime;
    }

    public Double getMemoryUsed() {
        return memoryUsed;
    }

    public void setMemoryUsed(Double memoryUsed) {
        this.memoryUsed = memoryUsed;
    }
}