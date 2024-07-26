package com.example.survey.exception;

public class SurveyNotFoundException extends RuntimeException {
    public SurveyNotFoundException(String message) {
        super(message);
    }
}
