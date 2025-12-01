package com.examples.algorythmtrainer.auth_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class RefreshJwtRequest {

    @Schema(description = "JWT токен обновления", example = "dGhpcyBpcyBhIHJlZnJlc2ggdG9rZW4...", required = true)
    @JsonProperty("refresh_token")
    public String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
