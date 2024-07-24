package com.psh10066.survey.common.adapter.in.web.response;

public record ErrorResponse(
    int code,
    String message
) {

    public static ErrorResponse of(CommonErrorType errorType, Exception e) {
        return new ErrorResponse(errorType.getCode(), e.getMessage());
    }
}
