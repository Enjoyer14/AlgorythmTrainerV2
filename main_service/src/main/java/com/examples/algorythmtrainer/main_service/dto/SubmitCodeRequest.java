package com.examples.algorythmtrainer.main_service.dto;


public class SubmitCodeRequest {
    private String code;
    private String language;

    public SubmitCodeRequest() {
    }

    public SubmitCodeRequest(String code, String language) {
        this.code = code;
        this.language = language;
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