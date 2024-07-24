package com.psh10066.survey.survey_management.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record RegisterSurveyResponse(
    @Schema(description = "설문조사 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    UUID id
) {
}
