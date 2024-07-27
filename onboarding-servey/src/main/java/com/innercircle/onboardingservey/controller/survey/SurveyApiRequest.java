package com.innercircle.onboardingservey.controller.survey;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SurveyApiRequest {
    public record AnswerApiCreateRequest(
        Long userId,
        Long surveyVersionId,
        List<QuestionAnswerApiCreateRequest> questionAnswerApiCreateRequests
    ) {

    }

    public record QuestionAnswerApiCreateRequest(
        QuestionApiRequest questionApiRequest,
        String textAnswer,
        List<Long> choiceAnswer
    ) {

    }

    public record QuestionApiRequest(
        Long questionId,
        String questionTitle,
        String questionDescription,
        Boolean isRequired,
        String questionType,
        List<SurveyApiRequest.QuestionOptionApiRequest> questionOptionApiRequests
    ) {

    }

    public record QuestionOptionApiRequest(
        String questionOptionTitle
    ) {

    }
}
