package fastcampus.innercircle.onboarding.survey.controller;

import fastcampus.innercircle.onboarding.survey.exception.SurveyQuestionEmptyException;
import fastcampus.innercircle.onboarding.survey.exception.SurveyQuestionOptionEmptyException;
import fastcampus.innercircle.onboarding.survey.exception.SurveyQuestionOptionNotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler({
            SurveyQuestionEmptyException.class,
            SurveyQuestionOptionEmptyException.class,
            SurveyQuestionOptionNotAllowedException.class
    })
    public ResponseEntity<String> badRequest(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> serverError(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
