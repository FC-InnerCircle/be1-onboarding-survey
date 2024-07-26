package com.innercircle.project_one.survey.api.dto;

import java.util.List;

public record SurveyDTO(
        Long version,
        String title,
        String description,
        List<SurveyObjectDTO> objects
) {

    public SurveyDTO(Long version, String title, String description, List<SurveyObjectDTO> objects) {
        this.version = version != null ? version : 0L;
        this.title = title;
        this.description = description;
        this.objects = objects;
    }
}
