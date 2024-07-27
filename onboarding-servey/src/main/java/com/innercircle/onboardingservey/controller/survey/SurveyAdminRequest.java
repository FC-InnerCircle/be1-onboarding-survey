package com.innercircle.onboardingservey.controller.survey;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SurveyAdminRequest {

    public record SurveyCreateAdminRequest(
        String surveyTitle,
        String surveyDescription,
        List<QuestionCreateAdminRequest> questionCreateAdminRequests
    ) {

    }


    public record QuestionCreateAdminRequest(
        String questionTitle,
        String questionDescription,
        Boolean isRequired,
        String questionType,
        List<QuestionOptionCreateAdminRequest> questionOptionCreateAdminRequests
    ) {

    }

    public record QuestionOptionCreateAdminRequest(
        String questionOptionTitle
    ) {

    }
}
