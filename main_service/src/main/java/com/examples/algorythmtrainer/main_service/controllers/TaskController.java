package com.examples.algorythmtrainer.main_service.controllers;

import com.examples.algorythmtrainer.main_service.models.Task;
import com.examples.algorythmtrainer.main_service.services.TaskService;
import com.examples.algorythmtrainer.main_service.repositories.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskRepository TaskRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(final TaskService taskService, final TaskRepository userRepository, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.TaskRepository = userRepository;
        this.taskRepository = taskRepository;
    }


    @GetMapping("/")
    public ResponseEntity<Optional<Task>> getAllTasks() {
        return ResponseEntity.ok(taskRepository.getAll());
    }


}
