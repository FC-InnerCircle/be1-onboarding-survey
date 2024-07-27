package com.inner_circle.survey.dto.response;

import java.util.List;

public record SurveyResponse(
    long id,
    String title,
    String description,
    List<QuestionResponse> questions
) {
}
