package com.innercircle.survey.common.exception;

import com.innercircle.survey.common.response.ErrorCode;

public class EntityNotFoundException extends BaseException{

    public EntityNotFoundException(ErrorCode errorCode) {
        super(ErrorCode.COMMON_ENTITY_NOT_FOUND);
    }

    public EntityNotFoundException(String message) {
        super(message, ErrorCode.COMMON_ENTITY_NOT_FOUND);
    }
}
