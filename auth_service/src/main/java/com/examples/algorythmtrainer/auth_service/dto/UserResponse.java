package com.examples.algorythmtrainer.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class UserResponse {

    @Schema(description = "Идентификатор пользователя", example = "1")
    private int id;

    @Schema(description = "Логин пользователя", example = "ivan123")
    private String login;

    @Schema(description = "Имя пользователя", example = "Иван")
    private String name;

    @Schema(description = "Адрес электронной почты пользователя", example = "ivan@mail.ru")
    private String email;

    @Schema(description = "Роль пользователя", example = "USER")
    private String role;

    public UserResponse(int id, String login, String name, String email, String role) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public UserResponse() {
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}