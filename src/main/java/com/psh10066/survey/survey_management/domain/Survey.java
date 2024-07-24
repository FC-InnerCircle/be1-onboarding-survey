package com.psh10066.survey.survey_management.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Survey {

    private final SurveyId id;
    private final SurveyForm form;

    private Survey(SurveyId id, SurveyForm form) {
        this.id = id;
        this.form = form;
    }

    public static Survey create(SurveyForm form) {
        return new Survey(
            new SurveyId(UUID.randomUUID()),
            form
        );
    }

    public static Survey update(SurveyId surveyId, SurveyForm form) {
        return new Survey(
            surveyId,
            form
        );
    }

    public record SurveyId(UUID value) {
    }
}
