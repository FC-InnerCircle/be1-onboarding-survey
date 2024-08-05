package com.innercircle.project_one.survey.api.dto;

import java.util.List;

public record SurveyObjectDTO(
        String type,
        String title,
        String description,
        List<String> elements,
        boolean isRequired
) {
}
