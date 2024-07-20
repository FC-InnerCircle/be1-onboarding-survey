package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.SurveyItem;

public record SurveyItemView(
        Long id,
        String name,
        String description,
        String form,
        Boolean required,
        Long sequence
) {
    public static SurveyItemView of(SurveyItem entity){
        return new SurveyItemView(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getForm().name(),
                entity.getRequired(),
                entity.getSequence()
        );
    }
}
