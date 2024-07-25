package lshh.be1onboardingsurvey.survey.domain.vo;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItemFormType;

public record SurveyResponseItemTextareaValue(
        String value
) implements SurveyResponseItemValue<String> {

    static SurveyResponseItemValue<String> of(String value) {
        return new SurveyResponseItemTextareaValue(value);
    }

    public SurveyItemFormType type() {
        return SurveyItemFormType.TEXTAREA;
    }
}
