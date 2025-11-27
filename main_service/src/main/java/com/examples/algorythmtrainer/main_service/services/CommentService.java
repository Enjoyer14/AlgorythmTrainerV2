package com.examples.algorythmtrainer.main_service.services;

import com.examples.algorythmtrainer.main_service.dto.CommentResponse;
import com.examples.algorythmtrainer.main_service.dto.TaskCommentRequest;
import com.examples.algorythmtrainer.main_service.models.Comment;
import com.examples.algorythmtrainer.main_service.models.TaskComment;
import com.examples.algorythmtrainer.main_service.models.TheoryComment;
import com.examples.algorythmtrainer.main_service.repositories.TaskCommentRepository;
import com.examples.algorythmtrainer.main_service.repositories.TaskRepository;
import com.examples.algorythmtrainer.main_service.repositories.TheoryCommentRepository;
import com.examples.algorythmtrainer.main_service.secure.JwtAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private TaskCommentRepository taskCommentRepository;
    private TheoryCommentRepository theoryCommentRepository;
    private TaskRepository taskRepository;

    @Autowired
    public CommentService(TaskCommentRepository taskCommentRepository, TheoryCommentRepository theoryCommentRepository) {
        this.taskCommentRepository = taskCommentRepository;
        this.theoryCommentRepository = theoryCommentRepository;
    }

    public List<CommentResponse> getTheoryComments(Integer theoryId) {
        List<TheoryComment> comments = theoryCommentRepository.findByTheory_TheoryId(theoryId);
        return comments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<CommentResponse> getTaskComments(Integer taskId) {
        List<TaskComment> comments = taskCommentRepository.findByTask_TaskId(taskId);
        return comments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public TaskComment addTaskComment(TaskCommentRequest comment, Integer taskId){
        Comment com = new Comment();
        JwtAuthentication jwta = new JwtAuthentication();

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
