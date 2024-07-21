package lshh.be1onboardingsurvey.survey.domain.vo;

import lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType;

public record SurveyResponseItemTextValue(String value) implements SurveyResponseItemValue<String> {
    static SurveyResponseItemValue<String> of(String value) {
        return new SurveyResponseItemTextValue(value);
    }

    @Override
    public SurveyItemFormType type() {
        return SurveyItemFormType.TEXT;
    }
}
