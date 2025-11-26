package com.examples.algorythmtrainer.main_service.secure;

import io.jsonwebtoken.Claims;

public final class JwtUtils {

    private JwtUtils() {}

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRole(claims.get("role", String.class));
        jwtInfoToken.setId(claims.get("id", Integer.class));
        jwtInfoToken.setLogin(claims.getSubject());
        return jwtInfoToken;
    }
}