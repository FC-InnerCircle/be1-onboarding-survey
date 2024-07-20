package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.Survey;

public record SurveyView(
        Long id,
        String name,
        String description

) {
    public static SurveyView of(Survey entity){
        return new SurveyView(entity.getId(), entity.getName(), entity.getDescription());
    }
}
