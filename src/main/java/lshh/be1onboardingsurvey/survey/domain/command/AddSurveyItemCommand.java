package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.SurveyItem;
import lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType;

public record AddSurveyItemCommand(
        Long surveyId,
        String name,
        String description,
        SurveyItemFormType form,
        Boolean required,
        Long sequence
){
    public AddSurveyItemCommand {
        if(surveyId == null){
            throw new IllegalArgumentException("SurveyId must not be null");
        }
    }

    public SurveyItem toEntity() {
        return SurveyItem.builder()
                .name(this.name)
                .description(this.description)
                .formType(this.form)
                .required(this.required)
                .sequence(this.sequence)
                .build();
    }
}
