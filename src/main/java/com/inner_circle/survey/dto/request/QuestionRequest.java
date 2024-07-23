package com.inner_circle.survey.dto.request;

import java.util.List;

public record QuestionRequest(
    String title,
    String description,
    int order,
    boolean required,
    List<OptionRequest> options
) {
}
