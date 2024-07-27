package com.fastcampus.innercircle.survey_api.common;

public class ExceptionResponse {

    private final String message;
    private final int code;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public ExceptionResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
