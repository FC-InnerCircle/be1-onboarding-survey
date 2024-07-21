package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType;
import lshh.be1onboardingsurvey.survey.domain.SurveyResponseItem;
import lshh.be1onboardingsurvey.survey.domain.vo.SurveyResponseItemValue;

public record AddSurveyResponseItemCommand(
        Long surveyId,
        Long responseId,
        Long itemId,
        String value
){
    public AddSurveyResponseItemCommand {
        if(surveyId == null || responseId == null || itemId == null){
            throw new IllegalArgumentException("SurveyId, responseId, itemId must not be null");
        }
    }

    public SurveyResponseItem toEntity(SurveyItemFormType type) {
        SurveyResponseItemValue<?> _value = SurveyResponseItemValue.of(type, value);
        return SurveyResponseItem.builder()
                .surveyItemId(itemId)
                .value(_value)
                .build();
    }

}
