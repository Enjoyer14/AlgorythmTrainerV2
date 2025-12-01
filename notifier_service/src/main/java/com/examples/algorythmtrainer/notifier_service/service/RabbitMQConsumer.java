package com.examples.algorythmtrainer.notifier_service.service;

import com.examples.algorythmtrainer.notifier_service.dto.ResultMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ObjectMapper objectMapper;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RabbitMQConsumer(SimpMessageSendingOperations messagingTemplate, ObjectMapper objectMapper) {
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "${rabbitmq.queue.results}")
    public void processResultMessage(String message) {
        try {
            log.info("Received message from RabbitMQ: {}", message);

            ResultMessage resultMessage = objectMapper.readValue(message, ResultMessage.class);
            Integer userId = resultMessage.getUserId();
            Integer submissionId = resultMessage.getSubmissionId();

            if (userId != null) {
                String room = String.format("user_%d", userId);
                String destination = String.format("/topic/%s", room);

                log.info("Sending result for submission {} to user {} at destination {}",
                        submissionId, userId, destination);

                messagingTemplate.convertAndSend(destination, resultMessage);

                log.info("Successfully sent result for submission {} to user {}",
                        submissionId, userId);
            } else {
                log.error("Error: Could not send result.  userId is null");
            }

        } catch (Exception e) {
            log.error("Critical error processing result message: {}", e.getMessage(), e);
        }
    }

}