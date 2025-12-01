package com.examples.algorythmtrainer.main_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp. rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.code-runner}")
    private String codeRunnerQueue;

    @Value("${rabbitmq.queue.durable}")
    private boolean durable;

    @Bean
    public Queue codeRunnerQueue() {
        return new Queue(codeRunnerQueue, durable);
    }

    @Bean
    public CachingConnectionFactory connectionFactory(
            @Value("${spring.rabbitmq.host}") String host,
            @Value("${spring.rabbitmq.port}") int port,
            @Value("${spring.rabbitmq.username}") String username,
            @Value("${spring.rabbitmq.password}") String password) {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}