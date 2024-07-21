package lshh.be1onboardingsurvey.survey.controller;

import lombok.extern.slf4j.Slf4j;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "lshh.be1onboardingsurvey.survey.controller")
public class SurveyControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    public Response<String> onIllegalArgumentException(IllegalArgumentException e) {
        log.warn("IllegalArgumentException: {}", e.getMessage());
        Result<String> result = Result.fail(e.getMessage());
        return Response.of(result);
    }

    @ExceptionHandler(Exception.class)
    public Response<String> onException(Exception e) {
        log.error("Exception: {}", e.getMessage());
        Result<String> result = Result.fail(e.getMessage());
        return Response.of(result);
    }
}
