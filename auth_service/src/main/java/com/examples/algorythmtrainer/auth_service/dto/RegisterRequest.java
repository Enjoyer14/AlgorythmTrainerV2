package com.examples.algorythmtrainer.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank
    @Size(min = 2, max = 255)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    private String login;

    @Size(max = 255)
    private String password;

    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank
    @Email
    private String email;
}
