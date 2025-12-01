package com.examples.algorythmtrainer.main_service.services;

import com.examples.algorythmtrainer.main_service.dto.TaskResponse;
import com.examples.algorythmtrainer.main_service.dto.TaskTestCaseExamplesResponse;
import com.examples.algorythmtrainer.main_service.dto.TasksResponse;
import com.examples.algorythmtrainer.main_service.models.Task;
import com.examples.algorythmtrainer.main_service.models.TaskTestCase;
import com.examples.algorythmtrainer.main_service.models.Theme;
import com.examples.algorythmtrainer.main_service.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    Logger log = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse getTaskById(Integer id){
        log.info("getTaskById called with id: " + id);
        Task task = taskRepository.findById(id).orElse(null);
        List<TaskTestCaseExamplesResponse> examples = task.getTestCases().stream()
                .filter(TaskTestCase::getIsExample)
                .map(ttc -> new TaskTestCaseExamplesResponse(ttc.getInputData(), ttc.getExpectedOutput()))
                .collect(Collectors.toList());

        return new TaskResponse( task.getTaskId(), task.getTitle(), task.getDescription(), task.getDifficultyLevel(),
                                task.getTimeLimitMs(), task.getMemoryLimitMb(), examples);
    }

    public List<TasksResponse> getAllTasks() {

        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private TasksResponse toDto(Task task) {
        return new TasksResponse(
                task.getTaskId(),
                task.getTitle(),
                task.getDifficultyLevel(),
                task.getTheme().getThemeId(),
                task.getTheme().getTitle()
        );
    }
}
