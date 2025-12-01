package com.examples.algorythmtrainer.main_service.services;

import com.examples.algorythmtrainer.main_service.dto.CommentResponse;
import com.examples.algorythmtrainer.main_service.dto.CommentRequest;
import com.examples.algorythmtrainer.main_service.models.*;
import com.examples.algorythmtrainer.main_service.repositories.*;
import com.examples.algorythmtrainer.main_service.secure.JwtAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private TaskCommentRepository taskCommentRepository;
    private TheoryCommentRepository theoryCommentRepository;
    private TaskRepository taskRepository;
    private CommentRepository commentRepository;
    private AlgorythmTheoryRepository algorythmTheoryRepository;

    Logger log = LoggerFactory.getLogger(CommentService.class);

    @Autowired
    public CommentService(TaskCommentRepository taskCommentRepository,
                          TheoryCommentRepository theoryCommentRepository,
                          TaskRepository taskRepository,
                          CommentRepository commentRepository,
                          AlgorythmTheoryRepository algorythmTheoryRepository) {
        this.taskCommentRepository = taskCommentRepository;
        this.theoryCommentRepository = theoryCommentRepository;
        this.taskRepository = taskRepository;
        this.commentRepository = commentRepository;
        this.algorythmTheoryRepository = algorythmTheoryRepository;
    }

    public List<CommentResponse> getTheoryComments(Integer theoryId) {
        log.info("Get Theory Comments for Theory Id: {}", theoryId);
        List<TheoryComment> comments = theoryCommentRepository.findByTheory_TheoryId(theoryId);
        return comments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<CommentResponse> getTaskComments(Integer taskId) {
        log.info("Get Task Comments for {}", taskId);
        List<TaskComment> comments = taskCommentRepository.findByTask_TaskId(taskId);
        return comments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CommentResponse addTaskComment(CommentRequest commentRequest, Integer taskId) {
        log.info("Add Task Comment called with commentRequest: {}", commentRequest);
        Integer userId = getCurrentUserId();

        Task task = taskRepository.findById(taskId).orElseThrow(() -> new IllegalArgumentException("Task not found with id: " + taskId));

        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setDescription(commentRequest.getDescription());
        comment.setDate(OffsetDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        TaskComment taskComment = new TaskComment();
        taskComment.setTask(task);
        taskComment.setComment(savedComment);

        TaskComment savedTaskComment = taskCommentRepository.save(taskComment);

        return new CommentResponse(savedTaskComment.getComment().getCommentId(),
                savedTaskComment.getComment().getUserId(),
                savedTaskComment.getComment().getDate(),
                savedTaskComment.getComment().getDescription());
    }

    public CommentResponse addTheoryComment(CommentRequest commentRequest, Integer theoryId) {
        log.info("Add Theory Comment called with id: " + theoryId);
        Integer userId = getCurrentUserId();

        AlgorythmTheory theory = algorythmTheoryRepository.findById(theoryId).orElseThrow(() -> new IllegalArgumentException("Theory not found with id: " + theoryId));

        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setDescription(commentRequest.getDescription());
        comment.setDate(OffsetDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        TheoryComment theoryComment = new TheoryComment();
        theoryComment.setTheory(theory);
        theoryComment.setComment(savedComment);

        TheoryComment savedTheoryComment = theoryCommentRepository.save(theoryComment);

        return new CommentResponse(savedTheoryComment.getComment().getCommentId(),
                savedTheoryComment.getComment().getUserId(),
                savedTheoryComment.getComment().getDate(),
                savedTheoryComment.getComment().getDescription());
    }

    private Integer getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthentication jwtAuth && jwtAuth.getId() != null) {
            log.info("Get Current User ID");
            return jwtAuth.getId();
        }
        log.error("Cannot determine current user id from JWT");
        throw new IllegalStateException("Cannot determine current user id from JWT");
    }

    private CommentResponse toDto(TheoryComment comment) {
        return new CommentResponse(
                comment.getTheoryCommentId(),
                comment.getComment().getUserId(),
                comment.getComment().getDate(),
                comment.getComment().getDescription()
        );
    }

    private CommentResponse toDto(TaskComment comment) {
        return new CommentResponse(
                comment.getTaskCommentId(),
                comment.getComment().getUserId(),
                comment.getComment().getDate(),
                comment.getComment().getDescription()
        );
    }

}
