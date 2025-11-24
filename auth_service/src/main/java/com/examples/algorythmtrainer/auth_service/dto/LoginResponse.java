package com.examples.algorythmtrainer.auth_service.dto;

public class LoginResponse {
    private final String type = "Bearer";

    private String accessToken;

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
