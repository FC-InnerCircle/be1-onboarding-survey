package com.inner_circle.survey.dto.response;

import com.inner_circle.survey.entity.request.AnswerType;

import java.util.List;

public record QuestionResponse(
    long id,
    AnswerType type,
    int order,
    String title,
    String description,
    boolean required,
    List<OptionResponse> options
) {
}
