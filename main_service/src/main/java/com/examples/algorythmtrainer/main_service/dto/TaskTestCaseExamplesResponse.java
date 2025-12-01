package com.examples.algorythmtrainer.main_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Пример тестового случая для задачи")
public class TaskTestCaseExamplesResponse {

    @Schema(description = "Пример входных данных", example = "1 2 3")
    private String input;

    @Schema(description = "Ожидаемый вывод программы", example = "6")
    private String output;

    public TaskTestCaseExamplesResponse() {
    }

    public TaskTestCaseExamplesResponse(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
