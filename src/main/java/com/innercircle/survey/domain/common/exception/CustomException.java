package com.innercircle.survey.domain.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String msg;

    @Override
    public String getMessage() {
        return "[%s] %s".formatted(errorCode, msg);
    }
}
