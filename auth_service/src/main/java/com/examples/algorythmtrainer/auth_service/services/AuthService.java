package com.examples.algorythmtrainer.auth_service.services;

import com.examples.algorythmtrainer.auth_service.dto.LoginRequest;
import com.examples.algorythmtrainer.auth_service.dto.LoginResponse;
import com.examples.algorythmtrainer.auth_service.dto.RegisterRequest;
import com.examples.algorythmtrainer.auth_service.models.User;
import com.examples.algorythmtrainer.auth_service.repositories.UserRepository;
import com.examples.algorythmtrainer.auth_service.secure.JwtAuthentication;
import com.examples.algorythmtrainer.auth_service.secure.JwtService;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    private final Map<String, String> refreshStorage = new HashMap<>();

    @Autowired
    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public LoginResponse login(@NonNull LoginRequest authRequest) throws AuthException {
        User user = userRepository.findUserByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            throw new AuthException("Неправильный пароль");
        }
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        refreshStorage.put(user.getLogin(), refreshToken);
        return new LoginResponse(accessToken, refreshToken);
    }

    public LoginResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtService.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtService.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findUserByLogin(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtService.generateAccessToken(user);
                return new LoginResponse(accessToken, null);
            }
        }
        return new LoginResponse(null, null);
    }

    public LoginResponse refresh(@NonNull String refreshToken) throws AuthException {
        if (jwtService.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtService.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findUserByLogin(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtService.generateAccessToken(user);
                final String newRefreshToken = jwtService.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new LoginResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }


    public @Nullable LoginResponse register(RegisterRequest req) {
    }
}
