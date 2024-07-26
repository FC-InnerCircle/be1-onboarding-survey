package com.innercircle.survey.controller.advice;

import com.innercircle.survey.exception.NotFoundException;
import com.innercircle.survey.exception.RequiredAnswerMissingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SurveyControllerAdvice {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(RequiredAnswerMissingException.class)
    public ResponseEntity<String> handleRequiredAnswerMissingException(RequiredAnswerMissingException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return ResponseEntity
                .internalServerError()
                .body(e.getMessage());
    }
}
