package com.examples.algorythmtrainer.main_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на создание комментария")
public class CommentRequest {

    @Schema(description = "Текст комментария", example = "Коммент")
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
