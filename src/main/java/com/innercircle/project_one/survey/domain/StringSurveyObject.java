package com.innercircle.project_one.survey.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.experimental.SuperBuilder;


@Entity
@SuperBuilder
@Getter
@DiscriminatorValue("STRING")
public class StringSurveyObject extends SurveyObject {
    private String textData;

}

