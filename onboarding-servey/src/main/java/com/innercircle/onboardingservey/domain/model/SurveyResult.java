package com.innercircle.onboardingservey.domain.model;

import java.util.Comparator;
import java.util.List;

public class SurveyResult {

    public record SurveyDetail(
        Long surveyId,
        String title,
        String description,
        List<QuestionDetail> questionDetails
    ) {

        public static SurveyDetail from(
            Survey survey,
            List<Question> questions
        ) {

            return new SurveyDetail(
                survey.getSurveyId(),
                survey.getTitle(),
                survey.getDescription(),
                questions.stream()
                    .map(QuestionDetail::from)
                    .toList()
            );
        }
    }

    public record QuestionDetail(
        Long questionId,
        String title,
        String description,
        Boolean required,
        QuestionType questionType,
        List<QuestionOptionDetail> questionOptions
    ) {

        public static QuestionDetail from(Question question) {
            return new QuestionDetail(
                question.getQuestionId(),
                question.getTitle(),
                question.getDescription(),
                question.getRequired(),
                question.getQuestionType(),
                question.getQuestionOptions()
                    .stream()
                    .sorted(Comparator.comparingInt(QuestionOption::getDisplayOrder))
                    .map(QuestionOptionDetail::from)
                    .toList()
            );
        }
    }

    public record QuestionOptionDetail(
        Long questOptionId,
        String title
    ) {

        public static QuestionOptionDetail from(QuestionOption questionOption) {
            return new QuestionOptionDetail(
                questionOption.getQuestionOptionId(), questionOption.getTitle()
            );
        }
    }
}
