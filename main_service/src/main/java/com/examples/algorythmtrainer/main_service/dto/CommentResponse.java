package com.examples.algorythmtrainer.main_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;

@Schema(description = "Ответ с данными комментария")
public class CommentResponse {

    @JsonProperty("comment_id")
    @Schema(description = "ID комментария", example = "10")
    Integer commentId;

    @JsonProperty("user_id")
    @Schema(description = "ID пользователя", example = "3")
    Integer userId;

    @Schema(description = "Дата и время создания комментария", example = "2024-01-01T12:30:00Z")
    OffsetDateTime date;

    @Schema(description = "Текст комментария", example = "Комментарий")
    String description;

    public CommentResponse() {
    }

    public CommentResponse(Integer commentId, Integer userId, OffsetDateTime date, String description) {
        this.commentId = commentId;
        this.userId = userId;
        this.date = date;
        this.description = description;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public void setDate(OffsetDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
