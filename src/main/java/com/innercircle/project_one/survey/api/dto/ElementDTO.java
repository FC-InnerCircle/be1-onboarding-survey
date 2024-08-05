package com.innercircle.project_one.survey.api.dto;

public record ElementDTO(
        String elementId,
        String elementValue,
        boolean selected
) {}