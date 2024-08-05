package com.innercircle.project_one.survey.api.dto;

import java.util.List;

public record SurveyAnswerListDTO(
        String answerId,
        String name,
        String description,
        List<SurveyListObjectDTO> objects
) {}