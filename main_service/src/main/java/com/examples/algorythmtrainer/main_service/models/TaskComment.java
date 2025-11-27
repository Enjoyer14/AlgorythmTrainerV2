package com.examples.algorythmtrainer.main_service.models;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "taskcomments")
public class TaskComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_comment_id")
    private Integer taskCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", unique = true, nullable = false)
    private Comment comment;

    public Integer getTaskCommentId() {
        return taskCommentId;
    }

    public void setTaskCommentId(Integer taskCommentId) {
        this.taskCommentId = taskCommentId;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}