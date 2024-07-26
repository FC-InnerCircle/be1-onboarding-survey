package com.innercircle.onboardingservey.domain.model;

import java.util.Comparator;
import java.util.List;

public class SurveyResult {

    public record SurveyDetailResult(
        Long surveyId,
        String title,
        String description,
        List<QuestionDetailResult> questionDetailResults
    ) {

        public static SurveyDetailResult from(
            Long surveyId,
            SurveyVersion surveyVersion,
            List<Question> questions
        ) {

            return new SurveyDetailResult(
                surveyId,
                surveyVersion.getTitle(),
                surveyVersion.getDescription(),
                questions.stream()
                    .map(QuestionDetailResult::from)
                    .toList()
            );
        }
    }

    public record QuestionDetailResult(
        Long questionId,
        String title,
        String description,
        Boolean required,
        QuestionType questionType,
        Integer displayOrder,
        List<QuestionOptionDetailResult> questionOptionDetails
    ) {

        public static QuestionDetailResult from(Question question) {
            return new QuestionDetailResult(
                question.getQuestionId(),
                question.getTitle(),
                question.getDescription(),
                question.getRequired(),
                question.getQuestionType(),
                question.getDisplayOrder(),
                question.getQuestionOptions()
                    .stream()
                    .sorted(Comparator.comparingInt(QuestionOption::getDisplayOrder))
                    .map(QuestionOptionDetailResult::from)
                    .toList()
            );
        }
    }

    public record QuestionOptionDetailResult(
        Long questOptionId,
        String title
    ) {

        public static QuestionOptionDetailResult from(QuestionOption questionOption) {
            return new QuestionOptionDetailResult(
                questionOption.getQuestionOptionId(),
                questionOption.getTitle()
            );
        }
    }
}
