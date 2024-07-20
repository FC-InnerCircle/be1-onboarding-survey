package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.SurveyItem;
import lshh.be1onboardingsurvey.survey.domain.SurveyItemForm;

public record AddSurveyItemCommand(
        Long surveyId,
        String name,
        String description,
        SurveyItemForm form,
        Boolean required
){

    public SurveyItem toEntity() {
        return SurveyItem.builder()
                .name(this.name)
                .description(this.description)
                .form(this.form)
                .required(this.required)
                .build();
    }
}
