package com.innercircle.onboardingservey.domain.model;

import com.innercircle.onboardingservey.domain.model.entity.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class SurveyResults {

    public record SurveyResult(
        Long surveyId,
        Long surveyVersionId,
        String title,
        String description,
        List<QuestionResult> questionResults
    ) {

        public static SurveyResult from(
            Long surveyId,
            SurveyVersion surveyVersion,
            List<Question> questions
        ) {

            return new SurveyResult(
                surveyId,
                surveyVersion.getSurveyVersionId(),
                surveyVersion.getTitle(),
                surveyVersion.getDescription(),
                questions.stream()
                    .map(QuestionResult::from)
                    .toList()
            );
        }
    }

    public record QuestionResult(
        Long questionId,
        String title,
        String description,
        Boolean required,
        QuestionType questionType,
        Integer displayOrder,
        List<QuestionOptionResult> questionOptionDetails
    ) {

        public static QuestionResult from(Question question) {
            return new QuestionResult(
                question.getQuestionId(),
                question.getTitle(),
                question.getDescription(),
                question.getRequired(),
                question.getQuestionType(),
                question.getDisplayOrder(),
                question.getQuestionOptions()
                    .stream()
                    .sorted(Comparator.comparingInt(QuestionOption::getDisplayOrder))
                    .map(QuestionOptionResult::from)
                    .toList()
            );
        }
    }

    public record QuestionOptionResult(
        Long questOptionId,
        String title
    ) {

        public static QuestionOptionResult from(QuestionOption questionOption) {
            return new QuestionOptionResult(
                questionOption.getQuestionOptionId(),
                questionOption.getTitle()
            );
        }
    }

    public record SurveyAnswerResult(
        Long userId,
        Long surveyId,
        Long surveyVersionId,
        String title,
        String description,
        List<SurveyAnswerQuestionResult> surveyAnswerQuestionResults
    ) {
        public static SurveyAnswerResult from(
            Long userId,
            SurveyVersion surveyVersion,
            List<Question> questions,
            Map<Long, List<QuestionAnswerDetail>> questionAnswerDetailsByQuestionIdMap
        ) {
            return new SurveyAnswerResult(
                userId,
                surveyVersion.getSurvey()
                    .getSurveyId(),
                surveyVersion.getSurveyVersionId(),
                surveyVersion.getTitle(),
                surveyVersion.getDescription(),
                questions.stream()
                    .map(question -> SurveyAnswerQuestionResult.from(
                        question,
                        questionAnswerDetailsByQuestionIdMap.get(question.getQuestionId())
                    ))
                    .toList()
            );
        }
    }

    public record SurveyAnswerQuestionResult(
        Long questionId,
        String questionTitle,
        String questionDescription,
        QuestionType questionType,
        List<QuestionOptionResult> questionOptionResults,
        String textAnswer,
        List<Long> choiceQuestionOptionId


    ) {
        public static SurveyAnswerQuestionResult from(
            Question question,
            List<QuestionAnswerDetail> questionAnswerDetails
        ) {

            return new SurveyAnswerQuestionResult(
                question.getQuestionId(),
                question.getTitle(),
                question.getDescription(),
                question.getQuestionType(),
                question.getQuestionOptions()
                    .stream()
                    .map(QuestionOptionResult::from)
                    .toList(),
                questionAnswerDetails.get(0)
                    .getTextAnswer(),
                questionAnswerDetails.stream()
                    .map(QuestionAnswerDetail::getChoiceQuestionOptionId)
                    .toList()
            );
        }
    }
}
