package com.innercircle.project_one.survey.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SuccessResponse<T>  extends ApiResponse {

    private T data;

    public SuccessResponse(String message) {
        super(HttpStatus.OK.value(), message);
    }

    public SuccessResponse(String message, T data) {
        super(HttpStatus.OK.value(), message);
        this.data = data;
    }
}
