package com.examples.algorythmtrainer.auth_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    private Environment environment;

    @Bean
    public OpenAPI defineOpenAPI () {
        Server server = new Server();

        Info info = new Info()
                .title("API для Аутентификации")
                .version("1.0")
                .description("API для регистрации/авторизации. ");
        return new OpenAPI().info(info).servers(List.of((server)));
    }
}
