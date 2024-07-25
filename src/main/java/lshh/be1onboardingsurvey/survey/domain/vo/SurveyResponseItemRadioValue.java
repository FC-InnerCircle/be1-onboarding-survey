package lshh.be1onboardingsurvey.survey.domain.vo;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItemFormType;

public record SurveyResponseItemRadioValue(
    Long value
) implements SurveyResponseItemValue<Long> {

    static SurveyResponseItemValue<Long> of(Long optionId) {
        return new SurveyResponseItemRadioValue(optionId);
    }

    @Override
    public Long value() {
        return this.value;
    }

    @Override
    public SurveyItemFormType type() {
        return SurveyItemFormType.RADIO;
    }
}
