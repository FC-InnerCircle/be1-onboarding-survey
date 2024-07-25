package com.psh10066.survey.survey_management.domain;

import com.psh10066.survey.survey_answer.domain.SurveyQuestionAnswer;
import com.psh10066.survey.survey_answer.domain.exception.SurveyAnswerException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Getter
@AllArgsConstructor
public enum SurveyQuestionType {

    SHORT(false) {
        @Override
        public void answerValid(SurveyQuestionAnswer answer) {
            if (!StringUtils.hasText(answer.textAnswer())) {
                throw new SurveyAnswerException(answer.surveyQuestionId() + "번 답변 누락");
            }
        }
    },
    LONG(false) {
        @Override
        public void answerValid(SurveyQuestionAnswer answer) {
            if (!StringUtils.hasText(answer.textAnswer())) {
                throw new SurveyAnswerException(answer.surveyQuestionId() + "번 답변 누락");
            }
        }
    },
    RADIO(true) {
        @Override
        public void answerValid(SurveyQuestionAnswer answer) {
            if (CollectionUtils.isEmpty(answer.selectInputValue())) {
                throw new SurveyAnswerException(answer.surveyQuestionId() + "번 답변 누락");
            }
            if (answer.selectInputValue().size() > 1) {
                throw new SurveyAnswerException(answer.surveyQuestionId() + "번 답변은 1개만 선택할 수 있습니다.");
            }
        }
    },
    CHECKBOX(true) {
        @Override
        public void answerValid(SurveyQuestionAnswer answer) {
            if (CollectionUtils.isEmpty(answer.selectInputValue())) {
                throw new SurveyAnswerException(answer.surveyQuestionId() + "번 답변 누락");
            }
        }
    };

    private final boolean selectable;

    public abstract void answerValid(SurveyQuestionAnswer answer);
}
