package com.innercircle.onboardingservey.controller.survey;

import com.innercircle.onboardingservey.domain.model.QuestionType;
import com.innercircle.onboardingservey.domain.model.SurveyResults;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SurveyApiResponse {
    public record SurveyDetailApiResponse(
        Long surveyVersionId,
        String title,
        String description,
        List<SurveyApiResponse.QuestionDetailApiResponse> questionDetailApiRespons
    ) {

        public static SurveyApiResponse.SurveyDetailApiResponse from(
            SurveyResults.SurveyResult surveyResult
        ) {

            return new SurveyApiResponse.SurveyDetailApiResponse(
                surveyResult.surveyVersionId(),
                surveyResult.title(),
                surveyResult.description(),
                surveyResult.questionResults()
                    .stream()
                    .map(SurveyApiResponse.QuestionDetailApiResponse::from)
                    .toList()
            );
        }
    }

    public record QuestionDetailApiResponse(
        Long questionId,
        String title,
        String description,
        Boolean required,
        QuestionType questionType,
        List<SurveyApiResponse.QuestionOptionDetailApiResponse> questionOptionsDetailResponse
    ) {

        public static SurveyApiResponse.QuestionDetailApiResponse from(SurveyResults.QuestionResult questionResult) {
            return new SurveyApiResponse.QuestionDetailApiResponse(
                questionResult.questionId(),
                questionResult.title(),
                questionResult.description(),
                questionResult.required(),
                questionResult.questionType(),
                questionResult.questionOptionDetails()
                    .stream()
                    .map(SurveyApiResponse.QuestionOptionDetailApiResponse::from)
                    .toList()
            );
        }
    }

    public record QuestionOptionDetailApiResponse(
        Long questOptionId,
        String title
    ) {

        public static SurveyApiResponse.QuestionOptionDetailApiResponse from(SurveyResults.QuestionOptionResult questionOptionResult) {
            return new SurveyApiResponse.QuestionOptionDetailApiResponse(
                questionOptionResult.questOptionId(),
                questionOptionResult.title()
            );
        }
    }
}
