package com.examples.algorythmtrainer.main_service.controllers;

import com.examples.algorythmtrainer.main_service.dto.CommentResponse;
import com.examples.algorythmtrainer.main_service.dto.CommentRequest;
import com.examples.algorythmtrainer.main_service.dto.TaskResponse;
import com.examples.algorythmtrainer.main_service.dto.TasksResponse;
import com.examples.algorythmtrainer.main_service.models.TaskComment;
import com.examples.algorythmtrainer.main_service.services.CommentService;
import com.examples.algorythmtrainer.main_service.services.TaskService;
import com.examples.algorythmtrainer.main_service.repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository taskRepository;
    private CommentService commentService;

    @Autowired
    public TaskController(TaskService taskService, TaskRepository taskRepository, CommentService commentService) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.commentService = commentService;
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

}
