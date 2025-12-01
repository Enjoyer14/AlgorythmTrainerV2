package com.examples.algorythmtrainer.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class LoginResponse {
    private final String type = "Bearer";

    @Schema(description = "JWT токен доступа", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    @JsonProperty("access_token")
    private String accessToken;

    @Schema(description = "JWT токен обновления", example = "dGhpcyBpcyBhIHJlZnJlc2ggdG9rZW4...")
    @JsonProperty("refresh_token")
    private String refreshToken;

    public LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getType() {
        return type;
    }
}
