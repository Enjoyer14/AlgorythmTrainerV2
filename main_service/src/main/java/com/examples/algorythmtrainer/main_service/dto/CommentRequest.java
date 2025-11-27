package com.examples.algorythmtrainer.main_service.dto;

public class CommentRequest {

    private String description;

    public CommentRequest() {
    }

    public CommentRequest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
