package com.innercircle.project_one.survey.common;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorEnum {
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "오류가 발생했습니다. 잠시 후 다시 시도해주세요."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "데이터가 존재하지 않습니다."),
    ID_NOT_FOUND(HttpStatus.NOT_FOUND, "ID가 존재하지 않습니다."),
    DATA_NOT_FOUND(HttpStatus.NOT_FOUND, "필수 데이터를 포함해야 합니다.");

    private final HttpStatus status;
    private final int code;
    private final String message;

    ErrorEnum(HttpStatus status, String message) {
        this.status = status;
        this.code = status.value();
        this.message = message;
    }
}