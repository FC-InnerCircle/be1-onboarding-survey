package com.inner_circle.survey.dto.request;

import java.util.List;

public record SurveyRequest(
    String title,
    String description,
    List<QuestionRequest> questions
) {
}
