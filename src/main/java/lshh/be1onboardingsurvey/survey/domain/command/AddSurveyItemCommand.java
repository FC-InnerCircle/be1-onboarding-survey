package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.SurveyItem;
import lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType;

public record AddSurveyItemCommand(
        Long surveyId,
        String name,
        String description,
        SurveyItemFormType formType,
        Boolean required,
        Long sequence
){
    public AddSurveyItemCommand {
        if(surveyId == null){
            throw new IllegalArgumentException("SurveyId must not be null");
        }
        if(formType == null){
            throw new IllegalArgumentException("Form must not be null");
        }
    }

    public SurveyItem toEntity() {
        return SurveyItem.builder()
                .name(this.name)
                .description(this.description)
                .formType(this.formType)
                .required(this.required)
                .sequence(this.sequence)
                .build();
    }
}
