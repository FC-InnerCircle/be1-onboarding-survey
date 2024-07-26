package com.psh10066.survey.survey_answer.adapter.in.web.exception;

import com.psh10066.survey.common.adapter.in.web.response.ErrorResponse;
import com.psh10066.survey.survey_answer.domain.exception.SurveyAnswerErrorType;
import com.psh10066.survey.survey_answer.domain.exception.SurveyAnswerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SurveyAnswerControllerAdvice {

    /**
     * 400
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SurveyAnswerException.class)
    ErrorResponse handleException(SurveyAnswerException e) {
        return new ErrorResponse(SurveyAnswerErrorType.SURVEY_ANSWER_NOT_VALID.getCode(), e.getMessage());
    }
}
