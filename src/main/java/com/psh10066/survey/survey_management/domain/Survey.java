package com.psh10066.survey.survey_management.domain;

import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@Getter
public class Survey {

    private final SurveyId id;
    private final SurveyForm form;

    private Survey(SurveyForm form) {
        this.id = new SurveyId();
        this.form = form;
    }

    public static Survey create(SurveyForm form) {
        return new Survey(form);
    }

    @Value
    public static class SurveyId {
        UUID value;

        public SurveyId() {
            this.value = UUID.randomUUID();
        }
    }
}
