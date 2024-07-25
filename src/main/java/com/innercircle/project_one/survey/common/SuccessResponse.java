package com.innercircle.project_one.survey.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SuccessResponse extends ApiResponse {
    private final String successMessage;

    public SuccessResponse(String successMessage) {
        this.successMessage = successMessage;
    }
}
