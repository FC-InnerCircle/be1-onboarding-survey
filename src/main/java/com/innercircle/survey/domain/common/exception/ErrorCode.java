package com.innercircle.survey.domain.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // Survey 관련 ErrorCode
    SURVEY_NOT_FOUND("해당 설문조사를 찾을 수 없습니다.");

    // SurveyResponse 관련 ErrorCode


    private final String msg;
}
