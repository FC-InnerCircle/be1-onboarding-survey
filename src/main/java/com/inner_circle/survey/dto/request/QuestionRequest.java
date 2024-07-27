package com.inner_circle.survey.dto.request;

import com.inner_circle.survey.entity.request.AnswerType;

import java.util.List;

public record QuestionRequest(
    String title,
    String description,
    AnswerType type,
    int order,
    boolean required,
    List<OptionRequest> options
) {
}
