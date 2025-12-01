package com.examples.algorythmtrainer.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema
public class RegisterRequest {

    @Schema(description = "Имя пользователя", example = "Иван", required = true)
    @NotBlank
    @Size(min = 2, max = 255)
    private String name;

    @Schema(description = "Логин пользователя", example = "ivan123", required = true)
    @NotBlank
    @Size(min = 3, max = 50)
    private String login;

    @Schema(description = "Пароль пользователя", example = "strongPassword!23", required = true)
    @Size(max = 255)
    private String password;

    @Schema(description = "Адрес электронной почты пользователя", example = "ivan@mail.ru", required = true)
    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank
    @Email
    private String email;

    public RegisterRequest() {
    }

    public RegisterRequest(String name, String login, String password, String email) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
