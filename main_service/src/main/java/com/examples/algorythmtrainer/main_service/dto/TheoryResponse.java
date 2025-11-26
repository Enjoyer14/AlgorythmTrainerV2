package com.examples.algorythmtrainer.main_service.dto;

public class TheoryResponse {
    private Integer theoryId;
    private Integer themeId;
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
