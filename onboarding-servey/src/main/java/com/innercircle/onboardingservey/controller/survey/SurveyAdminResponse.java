package com.innercircle.onboardingservey.controller.survey;


import com.innercircle.onboardingservey.domain.model.QuestionType;
import com.innercircle.onboardingservey.domain.model.SurveyResults.QuestionResult;
import com.innercircle.onboardingservey.domain.model.SurveyResults.QuestionOptionResult;
import com.innercircle.onboardingservey.domain.model.SurveyResults.SurveyResult;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SurveyAdminResponse {

    public record SurveyDetailAdminResponse(
        Long surveyId,
        String title,
        String description,
        List<QuestionDetailAdminResponse> questionDetailAdminRespons
    ) {

        public static SurveyDetailAdminResponse from(
            SurveyResult surveyResult
        ) {

            return new SurveyDetailAdminResponse(
                surveyResult.surveyId(),
                surveyResult.title(),
                surveyResult.description(),
                surveyResult.questionResults()
                    .stream()
                    .map(QuestionDetailAdminResponse::from)
                    .toList()
            );
        }
    }

    public record QuestionDetailAdminResponse(
        Long questionId,
        String title,
        String description,
        Boolean required,
        QuestionType questionType,
        List<QuestionOptionDetailAdminResponse> questionOptionsDetailResponse
    ) {

        public static QuestionDetailAdminResponse from(QuestionResult questionResult) {
            return new QuestionDetailAdminResponse(
                questionResult.questionId(),
                questionResult.title(),
                questionResult.description(),
                questionResult.required(),
                questionResult.questionType(),
                questionResult.questionOptionDetails()
                    .stream()
                    .map(QuestionOptionDetailAdminResponse::from)
                    .toList()
            );
        }
    }

    public record QuestionOptionDetailAdminResponse(
        Long questOptionId,
        String title
    ) {

        public static QuestionOptionDetailAdminResponse from(QuestionOptionResult questionOptionResult) {
            return new QuestionOptionDetailAdminResponse(
                questionOptionResult.questOptionId(),
                questionOptionResult.title()
            );
        }
    }
}
