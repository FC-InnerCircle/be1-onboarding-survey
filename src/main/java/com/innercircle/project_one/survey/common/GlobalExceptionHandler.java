package com.innercircle.project_one.survey.common;


import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.EnumSet;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final EnumSet<ErrorEnum> errorEnumSet = EnumSet.allOf(ErrorEnum.class);

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse> exceptionHandler(MethodArgumentNotValidException ex) {
        return toErrorResponseEntity(ErrorEnum.INVALID_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ApiResponse> exceptionHandler(IllegalArgumentException ex) {
        return toErrorResponseEntity(ErrorEnum.INVALID_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ApiResponse> exceptionHandler(HttpMessageNotReadableException ex) {
        return toErrorResponseEntity(ErrorEnum.INVALID_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiResponse> exceptionHandler(Exception ex) {
        return toErrorResponseEntity(ErrorEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<ApiResponse> toErrorResponseEntity(ErrorEnum errorEnum, String errorMsg) {
        ApiResponse apiResponse = new ErrorResponse(errorEnum, errorMsg);
        return new ResponseEntity<>(apiResponse, errorEnum.getStatus());
    }
}
