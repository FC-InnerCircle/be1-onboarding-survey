package com.inner_circle.survey.dto.request;

public record OptionRequest(
    String answer,
    int order
) {
}
