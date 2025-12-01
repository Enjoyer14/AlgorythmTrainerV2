package com.examples.algorythmtrainer.main_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Теоретический материал по теме")
public class TheoryResponse {

    @JsonProperty("theory_id")
    @Schema(description = "ID теории", example = "7")
    private Integer theoryId;

    @JsonProperty("theme_id")
    @Schema(description = "ID темы, к которой относится теория", example = "2")
    private Integer themeId;

    @Schema(description = "Текст теории", example = "Алгоритм быстрой сортировки работает по принципу разделяй и властвуй ...")
    private String description;

    public TheoryResponse() {
    }

    public TheoryResponse(Integer theoryId, Integer themeId, String description) {
        this.theoryId = theoryId;
        this.themeId = themeId;
        this.description = description;
    }

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public Integer getTheoryId() {
        return theoryId;
    }

    public void setTheoryId(Integer theoryId) {
        this.theoryId = theoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
