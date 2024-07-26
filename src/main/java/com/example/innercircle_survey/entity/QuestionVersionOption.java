package com.example.innercircle_survey.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class QuestionVersionOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_version_option_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_version_id")
    private QuestionVersion questionVersion;

    @Column(name = "question_version_option_text")
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public static QuestionVersionOption create(String text) {
        return QuestionVersionOption.builder()
                .text(text)
                .build();
    }
}
