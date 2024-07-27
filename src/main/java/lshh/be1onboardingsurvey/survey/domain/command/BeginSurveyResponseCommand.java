package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyResponse;

public record BeginSurveyResponseCommand(
        Long surveyId
){
    public BeginSurveyResponseCommand {
        if(surveyId == null){
            throw new IllegalArgumentException("SurveyId must not be null");
        }
    }

    public SurveyResponse toEntity() {
        return new SurveyResponse();
    }
}