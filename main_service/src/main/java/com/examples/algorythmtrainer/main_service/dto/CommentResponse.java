package com.examples.algorythmtrainer.main_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Date;

public class CommentResponse {

    @JsonProperty("comment_id")
    Integer commentId;

    @JsonProperty("user_id")
    Integer userId;

    OffsetDateTime date;
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
