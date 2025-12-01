package com.examples.algorythmtrainer.main_service. services;

import com.examples.algorythmtrainer.main_service.dto.SubmissionTaskMessage;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework. amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    Logger log = LoggerFactory.getLogger(RabbitMQProducer.class);

    @Value("${rabbitmq.queue.code-runner}")
    private String codeRunnerQueue;

    @Autowired
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public boolean publishSubmissionTask(Integer submissionId, Integer taskId, Integer userId, String code, String language) {
        try {
            SubmissionTaskMessage message = new SubmissionTaskMessage(submissionId, taskId, userId, code, language);

            rabbitTemplate.convertAndSend(codeRunnerQueue, message, msg -> {
                msg.getMessageProperties().setDeliveryMode(MessageDeliveryMode.fromInt(com.rabbitmq.client.MessageProperties.PERSISTENT_TEXT_PLAIN.getDeliveryMode()));
                return msg;
            });

            log.info("Submission task published successfully: submissionId={}, taskId={}", submissionId, taskId);
            return true;
        } catch (Exception e) {
            log.error("Error publishing submission task to RabbitMQ: submissionId={}, taskId={}, error={}",
                    submissionId, taskId, e.getMessage(), e);
            return false;
        }
    }
}