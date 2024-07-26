package com.psh10066.survey.survey_management.adapter.in.web.request.dto;

import com.psh10066.survey.survey_management.domain.SurveyQuestionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record SurveyQuestionRequestDto(
    @Schema(description = "항목 이름", requiredMode = Schema.RequiredMode.REQUIRED)
    String name,

    @Schema(description = "항목 설명", requiredMode = Schema.RequiredMode.REQUIRED)
    String description,

    @Schema(description = "항목 입력 형태", requiredMode = Schema.RequiredMode.REQUIRED)
    SurveyQuestionType type,

    @Schema(description = "선택 후보 (리스트 형태의 경우 필수 입력)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    List<String> selectInputs,

    @Schema(description = "항목 필수 여부", requiredMode = Schema.RequiredMode.REQUIRED)
    Boolean required
) {
}
