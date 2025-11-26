package com.examples.algorythmtrainer.main_service.repositories;

import com.examples.algorythmtrainer.main_service.models.AlgorythmTheory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlgorythmTheoryRepository extends JpaRepository<AlgorythmTheory, Integer> {
    AlgorythmTheory findByTheoryId(Integer id);
}
