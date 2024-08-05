package com.innercircle.project_one.survey.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ApiResponse {

    private int code;
    private String message;

    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public HttpStatus getHttpStatus() {
        return HttpStatus.valueOf(code);
    }
}

