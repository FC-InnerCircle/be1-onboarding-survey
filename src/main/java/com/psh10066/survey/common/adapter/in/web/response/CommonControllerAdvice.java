package com.psh10066.survey.common.adapter.in.web.response;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    /**
     * 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ErrorResponse handleException(ConstraintViolationException e) {
        return ErrorResponse.of(CommonErrorType.CONSTANT_VIOLATION, e);
    }

    /**
     * 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    ErrorResponse handleException(IllegalArgumentException e) {
        return ErrorResponse.of(CommonErrorType.ILLEGAL_ARGUMENT, e);
    }

    /**
     * 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JpaObjectRetrievalFailureException.class)
    ErrorResponse handleException(JpaObjectRetrievalFailureException e) {
        return ErrorResponse.of(CommonErrorType.ENTITY_NOT_FOUND, e);
    }

    /**
     * 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    ErrorResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ErrorResponse.of(CommonErrorType.INTERNAL_SERVER_ERROR, e);
    }
}
