package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.SurveyItem;
import lshh.be1onboardingsurvey.survey.domain.SurveyItemForm;


public record UpdateSurveyItemCommand(
        Long surveyId,
        Long itemId,
        String name,
        String description,
        SurveyItemForm form,
        Boolean required,
        Long sequence
){

    public SurveyItem toEntity() {
        return SurveyItem.builder()
                .name(this.name)
                .description(this.description)
                .form(this.form)
                .required(this.required)
                .sequence(this.sequence)
                .build();
    }
}