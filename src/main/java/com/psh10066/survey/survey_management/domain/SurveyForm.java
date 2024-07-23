package com.psh10066.survey.survey_management.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SurveyForm {
    private final Long version;
    private final String name;
    private final String description;
    private final List<SurveyQuestion> questions;

    public static SurveyForm create(String name, String description, List<SurveyQuestion> questions) {
        return new SurveyForm(1L, name, description, questions);
    }
}
