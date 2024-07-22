package com.example.innercircle_survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_version_id")
    private QuestionVersion questionVersion;

    @ManyToOne
    @JoinColumn(name = "response_id")
    private Response response;
}
