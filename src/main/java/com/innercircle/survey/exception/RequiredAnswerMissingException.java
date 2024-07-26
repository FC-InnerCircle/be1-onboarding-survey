package com.innercircle.survey.exception;

import lombok.Getter;

@Getter
public class RequiredAnswerMissingException extends RuntimeException {
    private final Long questionId;

    public RequiredAnswerMissingException(Long questionId) {
        super("Required answer is missing for question: " + questionId);
        this.questionId = questionId;
    }
}