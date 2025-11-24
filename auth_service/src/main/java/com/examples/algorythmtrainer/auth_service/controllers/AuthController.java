package com.examples.algorythmtrainer.auth_service.controllers;

import com.examples.algorythmtrainer.auth_service.dto.LoginRequest;
import com.examples.algorythmtrainer.auth_service.dto.LoginResponse;
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

    @GetMapping("/user/{id}")
    public ResponseEntity<userRequest> userById(@PathVariable int id) {
        return ResponseEntity.ok(authService.findUser(id));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws AuthException {
        final LoginResponse token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

}
