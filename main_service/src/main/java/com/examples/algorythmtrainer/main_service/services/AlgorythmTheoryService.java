package com.examples.algorythmtrainer.main_service.services;

import com.examples.algorythmtrainer.main_service.dto.TheoryResponse;
import com.examples.algorythmtrainer.main_service.models.AlgorythmTheory;
import com.examples.algorythmtrainer.main_service.repositories.AlgorythmTheoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AlgorythmTheoryService {

    private AlgorythmTheoryRepository algorythmTheoryRepository;

    @Autowired
    public AlgorythmTheoryService(AlgorythmTheoryRepository algorythmTheoryRepository) {
        this.algorythmTheoryRepository = algorythmTheoryRepository;
    }

    public TheoryResponse getAlgorythmTheory(int id) {
        AlgorythmTheory th = algorythmTheoryRepository.findByTheoryId(id);
        if (th == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new TheoryResponse(th.getTheoryId(), th.getTheme().getThemeId(), th.getDescription());
    }



}
