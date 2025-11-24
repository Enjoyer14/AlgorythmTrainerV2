package com.examples.algorythmtrainer.auth_service.secure;

import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRole(getRole(claims));
        jwtInfoToken.setId(claims.get("id", String.class));
        jwtInfoToken.setLogin(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<String> getRole(Claims claims) {
        final List<String> roles = claims.get("role", List.class);
        return roles.stream()
                .map(String::valueOf)
                .collect(Collectors.toSet());
    }

}
