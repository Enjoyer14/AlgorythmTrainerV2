package com.examples.algorythmtrainer.main_service.controllers;

import com.examples.algorythmtrainer.main_service.dto.*;
import com.examples.algorythmtrainer.main_service.models.Submission;
import com.examples.algorythmtrainer.main_service.models.Task;
import com.examples.algorythmtrainer.main_service.models.TaskComment;
import com.examples.algorythmtrainer.main_service.repositories.SubmissionRepository;
import com.examples.algorythmtrainer.main_service.secure.JwtAuthentication;
import com.examples.algorythmtrainer.main_service.services.CommentService;
import com.examples.algorythmtrainer.main_service.services.RabbitMQProducer;
import com.examples.algorythmtrainer.main_service.services.TaskService;
import com.examples.algorythmtrainer.main_service.repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private CommentService commentService;
    private final RabbitMQProducer rabbitMQProducer;
    private final SubmissionRepository submissionRepository;

    @Autowired
    public TaskController(TaskService taskService, TaskRepository taskRepository, CommentService commentService, RabbitMQProducer rabbitMQProducer, SubmissionRepository submissionRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.commentService = commentService;
        this.rabbitMQProducer = rabbitMQProducer;
        this.submissionRepository = submissionRepository;
    }


    @GetMapping("/")
    public ResponseEntity<List<TasksResponse>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Integer id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponse>> getTaskComments(@PathVariable int id) {
        return ResponseEntity.ok(commentService.getTaskComments(id));
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponse> addTaskComment(@PathVariable int id, @RequestBody CommentRequest taskCommentRequest) {
        CommentResponse saved = commentService.addTaskComment(taskCommentRequest, id);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/{id}/submit_code")
    public ResponseEntity<?> submitCode(@PathVariable Integer id, @RequestBody SubmitCodeRequest request) {
        try {
            Integer userId = getCurrentUserId();

            Task task = taskRepository.findById(id).orElse(null);

            if (task == null) {
                Map<String, String> error = new HashMap<>();
                error. put("msg", "Задача с таким ID не найдена");
                return ResponseEntity.status(404).body(error);
            }

            if (request.getCode() == null || request.getCode().trim().isEmpty()) {
                Map<String, String> error = new HashMap<>();
                error.put("msg", "Отсутствуют выполняемый код");
                return ResponseEntity.status(400).body(error);
            }

            String language = request.getLanguage() != null ?  request.getLanguage() : "python";

            Submission submission = new Submission();
            submission.setUserId(userId);
            submission. setTask(task);
            submission.setCode(request.getCode());
            submission.setIsComplete(false);
            submission.setStatus("PENDING");
            submission. setLanguage(language);
            submission. setDate(OffsetDateTime.now());

            Submission savedSubmission = submissionRepository.save(submission);
            Integer submissionId = savedSubmission. getSubmissionId();

            boolean success = rabbitMQProducer.publishSubmissionTask(
                    submissionId,
                    id,
                    userId,
                    request.getCode(),
                    language
            );

            if (success) {
                Map<String, Object> response = new HashMap<>();
                response.put("msg", "Код отправлен на проверку.  Ожидайте результата.");
                response.put("submission_id", submissionId);
                response.put("user_id", userId);
                return ResponseEntity.status(202).body(response);
            } else {
                Map<String, String> error = new HashMap<>();
                error.put("msg", "Сервис исполнения кода временно недоступен (RabbitMQ ошибка)");
                return ResponseEntity.status(503).body(error);
            }

        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("msg", "Внутренняя ошибка сервера");
            return ResponseEntity.status(500).body(error);
        }
    }


    private Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthentication jwtAuth && jwtAuth.getId() != null) {
            return jwtAuth.getId();
        }
        throw new IllegalStateException("Cannot determine current user id from JWT");
    }

}
