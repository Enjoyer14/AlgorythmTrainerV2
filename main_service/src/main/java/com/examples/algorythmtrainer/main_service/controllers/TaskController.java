package com.examples.algorythmtrainer.main_service.controllers;

import com.examples.algorythmtrainer.main_service.dto.*;
import com.examples.algorythmtrainer.main_service.models.Submission;
import com.examples.algorythmtrainer.main_service.models.Task;
import com.examples.algorythmtrainer.main_service.repositories.SubmissionRepository;
import com.examples.algorythmtrainer.main_service.secure.JwtAuthentication;
import com.examples.algorythmtrainer.main_service.services.CommentService;
import com.examples.algorythmtrainer.main_service.services.RabbitMQProducer;
import com.examples.algorythmtrainer.main_service.services.TaskService;
import com.examples.algorythmtrainer.main_service.repositories.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Tag(name = "Задачи", description = "Работа с задачами, комментариями и отправкой решений")
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private final CommentService commentService;
    private final RabbitMQProducer rabbitMQProducer;
    private final SubmissionRepository submissionRepository;

    Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    public TaskController(TaskService taskService, TaskRepository taskRepository,
                          CommentService commentService, RabbitMQProducer rabbitMQProducer,
                          SubmissionRepository submissionRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.commentService = commentService;
        this.rabbitMQProducer = rabbitMQProducer;
        this.submissionRepository = submissionRepository;
    }

    @Operation(summary = "Получить список задач")
    @GetMapping("/")
    public ResponseEntity<List<TasksResponse>> getAllTasks() {
        log.info("Get all tasks");
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Задача найдена"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена")
    })
    @Operation(summary = "Получить задачу по ID", description = "Возвращает описание задачи и пример тестов.")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(
            @Parameter( description = "ID задачи", required = true)
            @PathVariable Integer id) {
        log.info("Get task by id: {}", id);
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @Operation(summary = "Получить комментарии к задаче")
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentResponse>> getTaskComments(
            @Parameter( description = "ID задачи", required = true) @PathVariable int id) {
        log.info("Get task comment by id: {}", id);
        return ResponseEntity.ok(commentService.getTaskComments(id));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Комментарий добавлен"),
            @ApiResponse(responseCode = "401", description = "Пользователь не аутентифицирован")
    })
    @Operation(summary = "Добавить комментарий к задаче")
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentResponse> addTaskComment(
            @Parameter( description = "ID задачи", required = true) @PathVariable int id,
            @RequestBody CommentRequest taskCommentRequest) {
        log.info("Add task comment: {}", taskCommentRequest);
        CommentResponse saved = commentService.addTaskComment(taskCommentRequest, id);
        return ResponseEntity.ok(saved);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Код отправлен на проверку"),
            @ApiResponse(responseCode = "400", description = "Некорректные данные (например, пустой код)"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена"),
            @ApiResponse(responseCode = "503", description = "Очередь исполнения кода недоступна"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @Operation(
            summary = "Отправить решение задачи на проверку",
            description = "Принимает код и язык, создаёт отправку и публикует сообщение в RabbitMQ."
    )
    @PostMapping("/{id}/submit_code")
    public ResponseEntity<?> submitCode(
            @Parameter( description = "ID задачи", required = true) @PathVariable Integer id,
            @RequestBody SubmitCodeRequest request) {
        try {
            Integer userId = getCurrentUserId();

            Task task = taskRepository.findTaskByTaskId(id);

            if (task == null) {
                log.error("Task not found");
                Map<String, String> error = new HashMap<>();
                error.put("msg", "Задача с таким ID не найдена");
                return ResponseEntity.status(404).body(error);
            }

            if (request.getCode() == null || request.getCode().trim().isEmpty()) {
                log.error("Code is empty");
                Map<String, String> error = new HashMap<>();
                error.put("msg", "Отсутствуют выполняемый код");
                return ResponseEntity.status(400).body(error);
            }

            String language = request.getLanguage() != null ? request.getLanguage() : "python";

            Submission submission = new Submission();
            submission.setUserId(userId);
            submission.setTask(task);
            submission.setCode(request.getCode());
            submission.setIsComplete(false);
            submission.setStatus("PENDING");
            submission.setLanguage(language);
            submission.setDate(OffsetDateTime.now());

            Submission savedSubmission = submissionRepository.save(submission);
            Integer submissionId = savedSubmission.getSubmissionId();

            boolean success = rabbitMQProducer.publishSubmissionTask(
                    submissionId,
                    id,
                    userId,
                    request.getCode(),
                    language
            );
            log.info("Code submitted");

            if (success) {
                log.info("Code successfully submitted");
                Map<String, Object> response = new HashMap<>();
                response.put("msg", "Код отправлен на проверку. Ожидайте результата.");
                response.put("submission_id", submissionId);
                response.put("user_id", userId);
                return ResponseEntity.status(202).body(response);
            } else {
                log.error("Code failed");
                Map<String, String> error = new HashMap<>();
                error.put("msg", "Сервис исполнения кода временно недоступен (RabbitMQ ошибка)");
                return ResponseEntity.status(503).body(error);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
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
