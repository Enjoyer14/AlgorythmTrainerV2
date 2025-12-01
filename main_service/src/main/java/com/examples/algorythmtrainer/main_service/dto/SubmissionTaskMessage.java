package com.examples.algorythmtrainer.main_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@Schema(description = "Сообщение в очередь для проверки решения задачи")
public class SubmissionTaskMessage implements Serializable {

    @JsonProperty("submission_id")
    @Schema(description = "ID отправки решения", example = "100")
    private Integer submissionId;

    @JsonProperty("task_id")
    @Schema(description = "ID задачи", example = "5")
    private Integer taskId;

    @JsonProperty("user_id")
    @Schema(description = "ID пользователя", example = "3")
    private Integer userId;

    @JsonProperty("code")
    @Schema(description = "Код решения", example = "print(1+2)")
    private String code;

    @JsonProperty("language")
    @Schema(description = "ЯП решения", example = "python")
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
