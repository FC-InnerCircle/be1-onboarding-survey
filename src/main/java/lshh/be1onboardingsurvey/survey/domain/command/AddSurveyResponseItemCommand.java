package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItemFormType;
import lshh.be1onboardingsurvey.survey.domain.entity.SurveyResponseItem;
import lshh.be1onboardingsurvey.survey.domain.vo.SurveyResponseItemValue;

public record AddSurveyResponseItemCommand (
        Long surveyId,
        Long responseId,
        Long itemId,
        Object value
){
    public static AddSurveyResponseItemCommand of(AddSurveyResponseTextItemCommand command){
        return new AddSurveyResponseItemCommand(command.surveyId(), command.responseId(), command.itemId(), command.value());
    }
    public static AddSurveyResponseItemCommand of(AddSurveyResponseTextAreaItemCommand command){
        return new AddSurveyResponseItemCommand(command.surveyId(), command.responseId(), command.itemId(), command.value());
    }
    public static AddSurveyResponseItemCommand of(AddSurveyResponseRadioItemCommand command){
        return new AddSurveyResponseItemCommand(command.surveyId(), command.responseId(), command.itemId(), command.value());
    }
    public static AddSurveyResponseItemCommand of(AddSurveyResponseCheckboxItemCommand command){
        return new AddSurveyResponseItemCommand(command.surveyId(), command.responseId(), command.itemId(), command.value());
    }

    public SurveyResponseItem toEntity(SurveyItemFormType type) {
        SurveyResponseItemValue<?> _value = SurveyResponseItemValue.of(type, value);
        return SurveyResponseItem.builder()
                .surveyItemId(itemId)
                .value(_value)
                .build();
    }

}
