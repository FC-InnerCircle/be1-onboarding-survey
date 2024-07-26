package com.psh10066.survey.survey_answer.adapter.in.web.request.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record SurveyQuestionAnswerRequestDto(

    @Schema(description = "설문 항목 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    Long surveyQuestionId,

    @Schema(description = "설문 항목 텍스트 답변 (텍스트 타입일 경우 입력)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    String textAnswer,

    @Schema(description = "설문 항목 선택 답변 (선택 리스트 타입일 경우 입력)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    List<Integer> selectInputValue
) {
}
