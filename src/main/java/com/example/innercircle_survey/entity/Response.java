package com.example.innercircle_survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_version_id")
    private SurveyVersion surveyVersion;

    @OneToMany(mappedBy = "response")
    private List<Answer> answers;
}
