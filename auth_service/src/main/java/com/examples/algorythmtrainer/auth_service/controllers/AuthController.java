package com.examples.algorythmtrainer.auth_service.controllers;

import com.examples.algorythmtrainer.auth_service.dto.*;
import com.examples.algorythmtrainer.auth_service.models.User;
import com.examples.algorythmtrainer.auth_service.repositories.UserRepository;
import com.examples.algorythmtrainer.auth_service.secure.JwtFilter;
import com.examples.algorythmtrainer.auth_service.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно авторизован"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "401", description = "Неправильный пароль")
    })
    @Tag(name = "Аутентификация", description = "API для авторизации пользователя")
    @Operation(summary = "Авторизация пользователя", description = "Позволяет пользователю войти в систему, предоставляя свои учетные данные и получая JWT токены для доступа к защищенным ресурсам.")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) throws AuthException {
        log.info("Login request");
        final LoginResponse token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно зарегистрирован"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации данных регистрации"),
            @ApiResponse(responseCode = "409", description = "Пользователь с таким логином или email уже существует")
    })
    @Tag(name = "Аутентификация")
    @Operation(summary = "Регистрация пользователя", description = "Позволяет новому пользователю зарегистрироваться в системе, предоставляя необходимые данные для создания учетной записи.")
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody RegisterRequest req) throws AuthException {
        log.info("Register request");
        return ResponseEntity.ok(authService.register(req));
    }

    @Tag(name = "JWT", description = "API для получения/обновления JWT токенов")
    @Operation(summary = "Обновление JWT токенов", description = "Позволяет пользователю обновить свои JWT токены, предоставляя действительный refresh токен и получая новые access и refresh токены.")
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@RequestBody RefreshJwtRequest req) throws AuthException {
        log.info("Refresh request: {}", req);
        return ResponseEntity.ok(authService.refresh(req.getRefreshToken()));
    }

    @Tag(name = "JWT")
    @Operation(summary = "Получение Access токена", description = "Позволяет пользователю получить новый access токен, предоставляя действительный refresh токен.")
    @PostMapping("/access")
    public ResponseEntity<LoginResponse> getAccess(@RequestBody RefreshJwtRequest req) throws AuthException {
        log.info("Access request: {}", req);
        return ResponseEntity.ok(authService.getAccessToken(req.getRefreshToken()));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Информация о профиле успешно получена"),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @Tag(name = "Профиль", description = "API для получения информации о профиле")
    @Operation(summary = "Получение информации о профиле", description = "Позволяет пользователю получить информацию о своем профиле")
    @GetMapping("/me")
    public ResponseEntity<UserResponse> me() {
        log.info("Profile info");
        var auth = authService.getAuthInfo();
        User user = userRepository.findUserByLogin(auth.getLogin())
                .orElseThrow();
        return ResponseEntity.ok(new UserResponse(
                user.getId(),
                user.getLogin(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        ));
    }
}