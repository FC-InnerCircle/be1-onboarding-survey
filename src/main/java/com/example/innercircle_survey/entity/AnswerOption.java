package com.example.innercircle_survey.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class AnswerOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @Column(name = "answer_option_text")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
