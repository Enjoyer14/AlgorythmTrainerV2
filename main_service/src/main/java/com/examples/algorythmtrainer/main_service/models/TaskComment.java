package com.examples.algorythmtrainer.main_service.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "taskcomments")
@Data
public class TaskComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_comment_id")
    private Long taskCommentId;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @OneToOne
    @JoinColumn(name = "comment_id", unique = true, nullable = false)
    private Comment comment;

    public Long getTaskCommentId() {
        return taskCommentId;
    }

    public void setTaskCommentId(Long taskCommentId) {
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
