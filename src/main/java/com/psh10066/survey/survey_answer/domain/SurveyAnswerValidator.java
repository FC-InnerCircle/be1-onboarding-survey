package com.psh10066.survey.survey_answer.domain;

import com.psh10066.survey.survey_answer.domain.exception.SurveyAnswerException;
import com.psh10066.survey.survey_management.domain.Survey;
import com.psh10066.survey.survey_management.domain.SurveyQuestion;
import com.psh10066.survey.survey_management.domain.SurveySelectInput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SurveyAnswerValidator {

    public static void verify(Survey survey, SurveyAnswer surveyAnswer) {
        sameIdAndVersion(survey, surveyAnswer);
        correctAnswer(survey, surveyAnswer);
    }

    private static void sameIdAndVersion(Survey survey, SurveyAnswer surveyAnswer) {
        if (!survey.getId().equals(surveyAnswer.getSurveyId()) ||
            !survey.getForm().getVersion().equals(surveyAnswer.getSurveyVersion())) {
            throw new IllegalArgumentException("설문조사 ID 및 버전 매칭 오류");
        }
    }

    private static void correctAnswer(Survey survey, SurveyAnswer surveyAnswer) {
        List<SurveyQuestion> questions = survey.getForm().getQuestions();
        List<SurveyQuestionAnswer> answers = surveyAnswer.getAnswers();

        if (questions.size() != answers.size()) {
            throw new SurveyAnswerException("응답 사이즈 불일치");
        }
        if (questions.size() != new HashSet<>(answers).size()) {
            throw new SurveyAnswerException("응답 내 중복 값 존재");
        }
        for (SurveyQuestion question : questions) {
            SurveyQuestionAnswer answer = answers.stream()
                .filter(o -> o.surveyQuestionId().equals(question.id()))
                .findAny().orElseThrow(() -> new SurveyAnswerException(question.id() + "번 답변 누락"));

            if (!question.required()) {
                continue;
            }

            question.type().answerValid(answer);

            if (question.type().isSelectable()) {
                correctQuestionSelectAnswerCheck(question, answer.selectInputValue());
            }
        }
    }

    private static void correctQuestionSelectAnswerCheck(SurveyQuestion surveyQuestion, List<Integer> answerValues) {
        List<Integer> questionValues = surveyQuestion.selectInputs().stream()
            .map(SurveySelectInput::inputValue)
            .toList();

        if (answerValues.stream().noneMatch(questionValues::contains)) {
            throw new SurveyAnswerException(surveyQuestion.id() + "번 질문에 없는 답변");
        }
    }
}
