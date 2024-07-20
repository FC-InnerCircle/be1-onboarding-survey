package com.innercircle.onboardingservey.controller.survey;


import com.innercircle.onboardingservey.domain.model.QuestionType;
import com.innercircle.onboardingservey.domain.model.SurveyResult;
import java.util.List;

public class SurveyAdminResponse {

    public record SurveyDetail(
        Long surveyId,
        String title,
        String description,
        List<SurveyAdminResponse.QuestionDetail> questionDetails
    ) {

        public static SurveyAdminResponse.SurveyDetail from(
            SurveyResult.SurveyDetail surveyDetail
        ) {

            return new SurveyAdminResponse.SurveyDetail(
                surveyDetail.surveyId(),
                surveyDetail.title(),
                surveyDetail.description(),
                surveyDetail.questionDetails().stream().map(QuestionDetail::from).toList()
            );
        }
    }

    public record QuestionDetail(
        Long questionId,
        String title,
        String description,
        Boolean required,
        QuestionType questionType,
        List<SurveyAdminResponse.QuestionOptionDetail> questionOptions
    ) {

        public static SurveyAdminResponse.QuestionDetail from(SurveyResult.QuestionDetail question) {
            return new SurveyAdminResponse.QuestionDetail(
                question.questionId(),
                question.title(),
                question.description(),
                question.required(),
                question.questionType(),
                question.questionOptions()
                    .stream()
                    .map(QuestionOptionDetail::from)
                    .toList()
            );
        }
    }

    public record QuestionOptionDetail(
        Long questOptionId,
        String title
    ) {

        public static SurveyAdminResponse.QuestionOptionDetail from(SurveyResult.QuestionOptionDetail questionOptionDetail) {
            return new SurveyAdminResponse.QuestionOptionDetail(
                questionOptionDetail.questOptionId(), questionOptionDetail.title()
            );
        }
    }
}
