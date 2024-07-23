package com.innercircle.survey.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // Common
    INVALID_INPUT_VALUE(400, "C001", "잘못된 입력 값 입니다."),
    METHOD_NOT_ALLOWED(405, "C002", "허용되지 않은 메서드입니다."),
    ENTITY_NOT_FOUND(404, "C003", "엔티티를 찾을 수 없습니다,"),
    INTERNAL_SERVER_ERROR(500, "C004", "서버 내부 오류입니다."),
    ACCESS_DENIED(403, "C005", "접근이 거부되었습니다.,");

    private final int status;
    private final String code;
    private final String message;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
