package com.examples.algorythmtrainer.auth_service.controllers;

import com.examples.algorythmtrainer.auth_service.dto.*;
import com.examples.algorythmtrainer.auth_service.models.User;
import com.examples.algorythmtrainer.auth_service.repositories.UserRepository;
import com.examples.algorythmtrainer.auth_service.services.AuthService;
import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws AuthException {
        final LoginResponse token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }


    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegisterRequest req) throws AuthException {
        return ResponseEntity.ok(authService.register(req));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshJwtRequest req) throws AuthException {
        return ResponseEntity.ok(authService.refresh(req.getRefreshToken()));
    }

    @PostMapping("/access")
    public ResponseEntity<LoginResponse> getAccess(@RequestBody RefreshJwtRequest req) throws AuthException {
        return ResponseEntity.ok(authService.getAccessToken(req.getRefreshToken()));
    }

    @GetMapping("/me")
    public ResponseEntity<LoginResponse> me() {
        var auth = authService.getAuthInfo();
        User user = userRepository.findUserByLogin(auth.getLogin())
                .orElseThrow();
        return ResponseEntity.ok(new UserResponse(user.getId(), user.getLogin(), user.getName(), user.getEmail(), user.getRole()));
    }

}
