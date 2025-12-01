package com.examples.algorythmtrainer.auth_service.services;

import com.examples.algorythmtrainer.auth_service.controllers.AuthController;
import com.examples.algorythmtrainer.auth_service.dto.LoginRequest;
import com.examples.algorythmtrainer.auth_service.dto.LoginResponse;
import com.examples.algorythmtrainer.auth_service.dto.RegisterRequest;
import com.examples.algorythmtrainer.auth_service.models.User;
import com.examples.algorythmtrainer.auth_service.repositories.UserRepository;
import com.examples.algorythmtrainer.auth_service.secure.JwtAuthentication;
import com.examples.algorythmtrainer.auth_service.secure.JwtService;
import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    Logger log = LoggerFactory.getLogger(AuthController.class);

    private final Map<String, String> refreshStorage = new HashMap<>();

    @Autowired
    public AuthService(UserRepository userRepository,
                       JwtService jwtService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(@NonNull LoginRequest authRequest) throws AuthException {
        log.info("Login request: {}", authRequest.getLogin());
        User user = userRepository.findUserByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException("Пользователь не найден"));

        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            log.error("Wrong password for {}", authRequest.getLogin());
            throw new AuthException("Неправильный пароль");
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        refreshStorage.put(user.getLogin(), refreshToken);
        return new LoginResponse(accessToken, refreshToken);
    }

    public LoginResponse getAccessToken(@NonNull String refreshToken) throws AuthException {
        if (jwtService.validateRefreshToken(refreshToken)) {
            log.info("Refresh token: {}", refreshToken);
            final Claims claims = jwtService.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String savedRefreshToken = refreshStorage.get(login);

            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findUserByLogin(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtService.generateAccessToken(user);
                return new LoginResponse(accessToken, null);
            }
        }
        log.error("Invalid refresh token");
        throw new AuthException("Невалидный refresh-токен");
    }

    public LoginResponse refresh(@NonNull String refreshToken) throws AuthException {
        log.info("Refresh token: {}", refreshToken);
        if (jwtService.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtService.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String savedRefreshToken = refreshStorage.get(login);

            if (savedRefreshToken != null && savedRefreshToken.equals(refreshToken)) {
                final User user = userRepository.findUserByLogin(login)
                        .orElseThrow(() -> new AuthException("Пользователь не найден"));
                final String accessToken = jwtService.generateAccessToken(user);
                final String newRefreshToken = jwtService.generateRefreshToken(user);
                refreshStorage.put(user.getLogin(), newRefreshToken);
                return new LoginResponse(accessToken, newRefreshToken);
            }
        }
        log.error("Invalid refresh token");
        throw new AuthException("Невалидный JWT токен");
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    public @Nullable LoginResponse register(RegisterRequest req) throws AuthException {
        if (userRepository.findUserByLogin(req.getLogin()).isPresent()) {
            log.error("Login {} already exists", req.getLogin());
            throw new AuthException("Пользователь с таким логином уже существует");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            log.error("Email {} already exists", req.getEmail());
            throw new AuthException("Пользователь с таким email уже существует");
        }

        User user = new User();
        user.setName(req.getName());
        user.setLogin(req.getLogin());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        user = userRepository.save(user);

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        refreshStorage.put(user.getLogin(), refreshToken);

        return new LoginResponse(accessToken, refreshToken);
    }
}