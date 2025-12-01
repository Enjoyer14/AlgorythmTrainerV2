package com.examples.algorythmtrainer.notifier_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class RoomController {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ObjectMapper objectMapper;
    private static final String SESSION_ATTR_USER_ID = "user_id";

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public RoomController(SimpMessageSendingOperations messagingTemplate, ObjectMapper objectMapper) {
        this.messagingTemplate = messagingTemplate;
        this.objectMapper = objectMapper;
    }

    @MessageMapping("/join_submission_room")
    public void joinRoom(String payload, SimpMessageHeaderAccessor headerAccessor) {
        try {
            JsonNode data = objectMapper.readTree(payload);
            Integer userId = data.get("user_id").asInt();

            String sessionId = headerAccessor.getSessionId();
            headerAccessor.getSessionAttributes().put(SESSION_ATTR_USER_ID, userId);

            String room = String.format("user_%d", userId);
            log.info("Client {} (session {}) joined room {}", userId, sessionId, room);

            messagingTemplate. convertAndSend("/topic/" + room,
                    "{\"message\": \"User joined room\"}");

        } catch (Exception e) {
            log.error("Error in joinRoom: {}", e.getMessage(), e);
        }
    }

    @MessageMapping("/leave_submission_room")
    public void leaveRoom(String payload, SimpMessageHeaderAccessor headerAccessor) {
        try {
            JsonNode data = objectMapper.readTree(payload);
            Integer userId = data.get("user_id").asInt();

            String sessionId = headerAccessor. getSessionId();
            String room = String.format("user_%d", userId);

            log.info("Client {} (session {}) left room {}", userId, sessionId, room);

        } catch (Exception e) {
            log.error("Error in leaveRoom: {}", e.getMessage(), e);
        }
    }

}