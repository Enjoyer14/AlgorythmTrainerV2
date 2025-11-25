package com.examples.algorythmtrainer.main_service.models;

import jakarta.persistence.*;

@Entity
@Table(name = "algorythmtheories")
public class AlgorythmTheory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theory_id")
    private Integer theoryId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", unique = true, nullable = false)
    private Theme theme;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    public Integer getTheoryId() {
        return theoryId;
    }

    public void setTheoryId(Integer theoryId) {
        this.theoryId = theoryId;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}