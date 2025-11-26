package com.examples.algorythmtrainer.main_service.repositories;

import com.examples.algorythmtrainer.main_service.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    Task findTaskByTaskId(Integer taskId);
}
