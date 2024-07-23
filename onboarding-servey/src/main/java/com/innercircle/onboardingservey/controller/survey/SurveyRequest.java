package com.innercircle.onboardingservey.controller.survey;

import java.util.List;

public class SurveyRequest {

    public record SurveyCreateRequest(
        String surveyTitle,
        String surveyDescription,
        List<QuestionCreateRequest> questionCreateRequests
    ) {

    }

    public record SurveyUpdateRequest(
        Long surveyId,
        String surveyTitle,
        String surveyDescription,
        List<QuestionUpdateRequest> questionUpdateRequests
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

    public record QuestionUpdateRequest(
        Long questionId,
        String questionTitle,
        String questionDescription,
        Boolean isRequired,
        String questionType,
        List<QuestionOptionUpdateRequest> questionOptionUpdateRequests
    ) {

    }

    public record QuestionOptionCreateRequest(
        String questionOptionTitle
    ) {

    }

    public record QuestionOptionUpdateRequest(
        Long questionOptionId,
        String questionOptionTitle
    ) {

    }
}
