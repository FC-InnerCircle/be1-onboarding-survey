package com.innercircle.project_one.survey.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class SurveyVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int version;

    @OneToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;


    public SurveyVersion(int version, Survey survey) {
        this.version = version;
        this.survey  = survey;
    }

}

