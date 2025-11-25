package com.examples.algorythmtrainer.main_service.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_id", nullable = false)
    private Theme theme;

    @Column(name = "title", nullable = false, unique = true, length = 255)
    private String title;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "difficulty_level", nullable = false, length = 20)
    private String difficultyLevel;

    @Column(name = "time_limit_ms", nullable = false)
    private Integer timeLimitMs;

    @Column(name = "memory_limit_mb", nullable = false)
    private Integer memoryLimitMb;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskTestCase> testCases;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY)
    private List<Submission> submissions;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getTimeLimitMs() {
        return timeLimitMs;
    }

    public void setTimeLimitMs(Integer timeLimitMs) {
        this.timeLimitMs = timeLimitMs;
    }

    public Integer getMemoryLimitMb() {
        return memoryLimitMb;
    }

    public void setMemoryLimitMb(Integer memoryLimitMb) {
        this.memoryLimitMb = memoryLimitMb;
    }

    public List<TaskTestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TaskTestCase> testCases) {
        this.testCases = testCases;
    }

    public List<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<Submission> submissions) {
        this.submissions = submissions;
    }
}