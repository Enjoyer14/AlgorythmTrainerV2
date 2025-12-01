package com.examples.algorythmtrainer.auth_service.exceptions;

import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Map<String, String>> handleAuthException(AuthException ex) {

        String message = ex.getMessage();
        if (message == null) {
            message = "Ошибка аутентификации";
        }

        Map<String, String> body = new HashMap<>();
        body.put("error", message);

        HttpStatus status;
        if (message.contains("не найден")) {
            status = HttpStatus.NOT_FOUND;
        } else if (message.toLowerCase().contains("парол")) {
            status = HttpStatus.UNAUTHORIZED;
        } else {
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity<>(body, status);
    }
}
