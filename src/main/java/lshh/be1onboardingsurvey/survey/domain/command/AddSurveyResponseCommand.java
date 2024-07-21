package lshh.be1onboardingsurvey.survey.domain.command;

import lshh.be1onboardingsurvey.survey.domain.SurveyResponse;

public record AddSurveyResponseCommand (
        Long surveyId
){
    public AddSurveyResponseCommand {
        if(surveyId == null){
            throw new IllegalArgumentException("SurveyId must not be null");
        }
    }

    public SurveyResponse toEntity() {
        return new SurveyResponse();
    }
}