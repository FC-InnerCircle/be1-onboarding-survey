package com.inner_circle.survey.dto.response;

public record OptionResponse(
    long id,
    int order,
    String answer
) {
}
