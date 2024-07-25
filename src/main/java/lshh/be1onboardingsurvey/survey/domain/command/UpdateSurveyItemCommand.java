package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItem;
import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItemFormType;

public record UpdateSurveyItemCommand(
        Long surveyId,
        Long itemId,
        String name,
        String description,
        SurveyItemFormType form,
        Boolean required,
        Long sequence
){
    public UpdateSurveyItemCommand {
        if(surveyId == null || itemId == null){
            throw new IllegalArgumentException("SurveyId, itemId must not be null");
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