package com.examples.algorythmtrainer.main_service.repositories;

import com.examples.algorythmtrainer.main_service.models.Task;
import com.examples.algorythmtrainer.main_service.models.TaskComment;
import com.examples.algorythmtrainer.main_service.models.TheoryComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskCommentRepository extends JpaRepository<TaskComment, Integer> {
    List<TaskComment> findByTask_TaskId(Integer taskId);
}
