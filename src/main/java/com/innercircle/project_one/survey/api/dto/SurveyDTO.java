package com.innercircle.project_one.survey.api.dto;

import java.util.List;

public record SurveyDTO(
        String title,
        String description,
        List<SurveyObjectDTO> objects
) {
}
