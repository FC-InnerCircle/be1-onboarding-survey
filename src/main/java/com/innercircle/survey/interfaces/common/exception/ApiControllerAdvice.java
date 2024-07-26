package com.innercircle.survey.interfaces.common.exception;

import com.innercircle.survey.domain.common.exception.CustomException;
import com.innercircle.survey.interfaces.common.dto.ApiResultResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j(topic = "ExceptionLogger")
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(CustomException.class)
    @ApiResponse(responseCode = "200", description = "CustomException")
    public ApiResultResponse<Object> handlerCustomException(CustomException e) {
        log.info("CustomException is occurred! {}", e.getMessage());
        return ApiResultResponse.of(HttpStatus.OK, false, e.getMessage(), null);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiResultResponse<Object> bindException(BindException e) {
        log.warn("BindException is occurred! {}", e.getMessage());
        return ApiResultResponse.of(HttpStatus.BAD_REQUEST, false,
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage(), null);
    }


//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(Exception.class)
//    @ApiResponse(responseCode = "500", description = "Exception")
//
//    public ApiResultResponse<Object> handlerException(Exception e) {
//        log.error("Exception is occurred! {}", e.getMessage());
//        return ApiResultResponse.of(HttpStatus.INTERNAL_SERVER_ERROR, false, null);
//    }
}
