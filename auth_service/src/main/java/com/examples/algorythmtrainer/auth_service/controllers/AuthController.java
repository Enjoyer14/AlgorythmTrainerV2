package com.examples.algorythmtrainer.auth_service.controllers;

import com.examples.algorythmtrainer.auth_service.dto.LoginRequest;
import com.examples.algorythmtrainer.auth_service.dto.LoginResponse;
import com.examples.algorythmtrainer.auth_service.dto.RefreshJwtRequest;
import com.examples.algorythmtrainer.auth_service.dto.userRequest;
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

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws AuthException {
        final LoginResponse token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }


    @PostMapping("/token")
    public ResponseEntity<LoginResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final LoginResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) throws AuthException {
        final LoginResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }

}
