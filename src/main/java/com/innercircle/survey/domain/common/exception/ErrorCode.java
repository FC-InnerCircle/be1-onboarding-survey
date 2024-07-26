package com.innercircle.survey.domain.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    // Survey 관련 ErrorCode
    SURVEY_NOT_FOUND("해당 설문 조사를 찾을 수 없습니다."),
    SURVEY_REGISTER_FAILED("설문 조사 등록에 실패하였습니다"),

    // SurveyItem 관련 ErrorCode
    SURVEY_ITEM_REGISTER_FAILED("설문 조사 항목 등록에 실패하였습니다."),

    // SurveyItemOption 관련 ErrorCode
    SURVEY_ITEM_OPTION_REGISTER_FAILED("설문 조사 항목 형태 등록에 실패하였습니다.");


    // SurveyResponse 관련 ErrorCode


    private final String msg;
}
