package com.examples.algorythmtrainer.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(description = "Запрос для авторизации пользователя")
@Data
public class LoginRequest {

    @Schema(description = "Логин пользователя", example = "user123", required = true)
    @NotBlank
    @Size(min = 3, max = 50)
    private String login;

    @Schema(description = "Пароль пользователя", example = "strongPassword!23", required = true)
    @NotBlank
    @Size(min = 3, max = 255, message = "Длина пароля должна быть от 8 до 255 символов")
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
