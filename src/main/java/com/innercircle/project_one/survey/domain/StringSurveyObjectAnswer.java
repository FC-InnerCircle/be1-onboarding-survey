package com.innercircle.project_one.survey.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STRING")
public class StringSurveyObjectAnswer extends SurveyObjectAnswer {
    private String answer;

}