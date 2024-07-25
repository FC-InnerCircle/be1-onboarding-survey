package lshh.be1onboardingsurvey.survey.domain.vo;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItemFormType;

public record SurveyResponseItemCheckboxValue(
    Long[] value
) implements SurveyResponseItemValue<Long[]> {

    static SurveyResponseItemValue<Long[]> of(Long[] optionIds) {
        return new SurveyResponseItemCheckboxValue(optionIds);
    }

    @Override
    public Long[] value() {
        return this.value;
    }

    @Override
    public SurveyItemFormType type() {
        return SurveyItemFormType.CHECKBOX;
    }

}
