package com.innercircle.project_one.survey.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Objects;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ErrorResponse extends ApiResponse {

    public ErrorResponse(ErrorEnum errorEnum, String errorMessage) {
        super(errorEnum.getCode(), errorMessage);
    }

}
