package com.examples.algorythmtrainer.main_service.repositories;

import com.examples.algorythmtrainer.main_service. models.Submission;
import org.springframework.data.jpa. repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    List<Submission> findByUserId(Integer userId);
    List<Submission> findByTaskId(Integer taskId);
}