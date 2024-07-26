package com.example.innercircle_survey.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @Builder
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

    public static AnswerOption create(String text) {
        return AnswerOption.builder()
                .text(text)
                .build();
    }
}
