package com.examples.algorythmtrainer.main_service.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "theorycomments")
@Data
public class TheoryComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theory_comment_id")
    private Long theoryCommentId;

    @ManyToOne
    @JoinColumn(name = "theory_id", nullable = false)
    private AlgorythmTheory theory;


    @OneToOne
    @JoinColumn(name = "comment_id", unique = true, nullable = false)
    private Comment comment;

    public Long getTheoryCommentId() {
        return theoryCommentId;
    }

    public void setTheoryCommentId(Long theoryCommentId) {
        this.theoryCommentId = theoryCommentId;
    }

    public AlgorythmTheory getTheory() {
        return theory;
    }

    public void setTheory(AlgorythmTheory theory) {
        this.theory = theory;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}