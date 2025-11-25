package com.examples.algorythmtrainer.main_service.models;

import jakarta.persistence.*;

import java.util.List;

public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theme_id")
    private Integer themeId;

    @Column(name = "title", nullable = false, unique = true, length = 100)
    private String title;

    @ManyToOne
    @JoinColumn(name = "parent_theme_id")
    private Theme parentTheme;

    @OneToMany(mappedBy = "parentTheme")
    private List<Theme> subthemes;

    @OneToOne(mappedBy = "theme")
    private AlgorythmTheory theory;

    @OneToMany(mappedBy = "theme")
    private List<Task> tasks;

    public Integer getThemeId() {
        return themeId;
    }

    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Theme getParentTheme() {
        return parentTheme;
    }

    public void setParentTheme(Theme parentTheme) {
        this.parentTheme = parentTheme;
    }

    public List<Theme> getSubthemes() {
        return subthemes;
    }

    public void setSubthemes(List<Theme> subthemes) {
        this.subthemes = subthemes;
    }

    public AlgorythmTheory getTheory() {
        return theory;
    }

    public void setTheory(AlgorythmTheory theory) {
        this.theory = theory;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
