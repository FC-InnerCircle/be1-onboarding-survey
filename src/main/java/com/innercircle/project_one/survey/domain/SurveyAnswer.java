package com.innercircle.project_one.survey.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SurveyAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;


    @JoinColumn(name = "survey_version_id")
    private Long surveyVersionId;

    public SurveyAnswer(Survey survey) {
        this.survey = survey;
        this.surveyVersionId = survey.getSurveyVersion().getVersion();
    }
}
