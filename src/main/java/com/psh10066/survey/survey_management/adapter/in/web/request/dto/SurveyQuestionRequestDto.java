package com.psh10066.survey.survey_management.adapter.in.web.request.dto;

import com.psh10066.survey.survey_management.domain.SurveyQuestionType;

import java.util.List;

public record SurveyQuestionRequestDto(
    String name,
    String description,
    SurveyQuestionType type,
    List<String> selectInputs,
    Boolean required
) {
}
