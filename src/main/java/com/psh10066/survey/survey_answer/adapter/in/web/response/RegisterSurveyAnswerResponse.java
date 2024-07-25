package com.psh10066.survey.survey_answer.adapter.in.web.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record RegisterSurveyAnswerResponse(
    @Schema(description = "설문조사 응답 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    Long id
) {
}
