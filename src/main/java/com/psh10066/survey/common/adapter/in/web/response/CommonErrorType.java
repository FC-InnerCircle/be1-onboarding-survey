package com.psh10066.survey.common.adapter.in.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorType {

    CONSTANT_VIOLATION(-1),
    ILLEGAL_ARGUMENT(-2),
    ENTITY_NOT_FOUND(-3),
    INTERNAL_SERVER_ERROR(-999);

    private final int code;
}
