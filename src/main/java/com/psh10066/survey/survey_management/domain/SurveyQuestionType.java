package com.psh10066.survey.survey_management.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SurveyQuestionType {

    SHORT(false),
    LONG(false),
    RADIO(true),
    CHECKBOX(true);

    private final boolean selectable;
}
