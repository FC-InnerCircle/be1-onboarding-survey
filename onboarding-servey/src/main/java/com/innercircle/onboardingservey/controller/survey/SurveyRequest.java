package com.innercircle.onboardingservey.controller.survey;

import java.util.List;

public class SurveyRequest {

    public record SurveyCreateRequest(
        String surveyTitle,
        String surveyDescription,
        List<QuestionCreateRequest> questionCreateRequests
    ) {

    }

    public record QuestionCreateRequest(
        String questionTitle,
        String questionDescription,
        Boolean isRequired,
        String questionType,
        List<QuestionOptionCreateRequest> questionOptionCreateRequests
    ) {

    }

    public record QuestionOptionCreateRequest(
        String questionOptionTitle
    ) {

    }
}
