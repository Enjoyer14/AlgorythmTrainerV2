package com.examples.algorythmtrainer.main_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрос на отправку кода решения задачи")
public class SubmitCodeRequest {

    @Schema(description = "Код решения", example = "print(sum(map(int, input().split())))")
    private String code;

    @Schema(description = "ЯП решения", example = "python")
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
