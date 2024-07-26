package com.example.survey.adapter.exception;

import com.example.survey.adapter.dto.ApiResponse;
import com.example.survey.exception.SurveyNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage();
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status("error")
                .message(errorMessage)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status("error")
                .message("An unexpected error occurred")
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SurveyNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleSurveyNotFoundException(SurveyNotFoundException ex) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
            .status("error")
            .message(ex.getMessage())
            .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
