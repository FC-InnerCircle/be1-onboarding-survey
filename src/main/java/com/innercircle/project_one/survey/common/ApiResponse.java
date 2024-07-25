package com.innercircle.project_one.survey.common;

import org.springframework.http.HttpStatus;

public abstract class ApiResponse {
    protected int code = HttpStatus.OK.value();

    public HttpStatus getHttpStatus() {
        try {
            return HttpStatus.valueOf(code);
        } catch (IllegalArgumentException e) {
            return HttpStatus.valueOf(ErrorEnum.INVALID_REQUEST.getCode());
        }
    }
}
