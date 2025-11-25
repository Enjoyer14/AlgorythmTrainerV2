package com.examples.algorythmtrainer.main_service.models;

import jakarta.persistence.*;

@Entity
@Table(name = "theorycomments")
public class TheoryComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theory_comment_id")
    private Integer theoryCommentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theory_id", nullable = false)
    private AlgorythmTheory theory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", unique = true, nullable = false)
    private Comment comment;

    public Integer getTheoryCommentId() {
        return theoryCommentId;
    }

    public void setTheoryCommentId(Integer theoryCommentId) {
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