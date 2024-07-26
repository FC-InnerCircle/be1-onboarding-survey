package com.psh10066.survey.survey_answer.adapter.in.web.response.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record FindSurveyAnswerResponseDto(

    @Schema(description = "설문조사 버전", requiredMode = Schema.RequiredMode.REQUIRED)
    Long surveyVersion,

    @Schema(description = "설문조사 응답 항목 목록", requiredMode = Schema.RequiredMode.REQUIRED)
    List<SurveyQuestionAnswerResponseDto> answers,

    @Schema(description = "설문 응답 시각", requiredMode = Schema.RequiredMode.REQUIRED)
    LocalDateTime createdAt
) {
}
