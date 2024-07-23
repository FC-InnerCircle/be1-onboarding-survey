package com.innercircle.onboardingservey.controller.survey;


import com.innercircle.onboardingservey.domain.model.QuestionType;
import com.innercircle.onboardingservey.domain.model.SurveyResult.QuestionDetailResult;
import com.innercircle.onboardingservey.domain.model.SurveyResult.QuestionOptionDetailResult;
import com.innercircle.onboardingservey.domain.model.SurveyResult.SurveyDetailResult;

import java.util.List;

public class SurveyAdminResponse {

    public record SurveyDetailResponse(
        Long surveyId,
        String title,
        String description,
        List<QuestionDetailResponse> questionDetailResponses
    ) {

        public static SurveyDetailResponse from(
            SurveyDetailResult surveyDetailResult
        ) {

            return new SurveyDetailResponse(
                surveyDetailResult.surveyId(),
                surveyDetailResult.title(),
                surveyDetailResult.description(),
                surveyDetailResult.questionDetailResults()
                    .stream()
                    .map(QuestionDetailResponse::from)
                    .toList()
            );
        }
    }

    public record QuestionDetailResponse(
        Long questionId,
        String title,
        String description,
        Boolean required,
        QuestionType questionType,
        List<QuestionOptionDetailResponse> questionOptionsDetailResponse
    ) {

        public static QuestionDetailResponse from(QuestionDetailResult questionDetailResult) {
            return new QuestionDetailResponse(
                questionDetailResult.questionId(),
                questionDetailResult.title(),
                questionDetailResult.description(),
                questionDetailResult.required(),
                questionDetailResult.questionType(),
                questionDetailResult.questionOptionDetails()
                    .stream()
                    .map(QuestionOptionDetailResponse::from)
                    .toList()
            );
        }
    }

    public record QuestionOptionDetailResponse(
        Long questOptionId,
        String title
    ) {

        public static QuestionOptionDetailResponse from(QuestionOptionDetailResult questionOptionDetailResult) {
            return new QuestionOptionDetailResponse(
                questionOptionDetailResult.questOptionId(),
                questionOptionDetailResult.title()
            );
        }
    }
}
