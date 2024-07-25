package com.psh10066.survey.survey_answer.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SurveyAnswerErrorType {

    SURVEY_ANSWER_NOT_VALID(-101);

    private final int code;
}
