package com.examples.algorythmtrainer.main_service.repositories;

import com.examples.algorythmtrainer.main_service.models.TaskComment;
import com.examples.algorythmtrainer.main_service.models.TheoryComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheoryCommentRepository extends JpaRepository<TheoryComment, Integer> {
    List<TheoryComment> findByTheory_TheoryId(Integer theoryId);
}
