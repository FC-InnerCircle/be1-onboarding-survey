package com.innercircle.project_one.survey.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("STRING")
public class StringSurveyObjectAnswer extends SurveyObjectAnswer {
    private String answer;

}