package com.psh10066.survey.survey_management.adapter.in.web.request;

import com.psh10066.survey.survey_management.adapter.in.web.request.dto.SurveyQuestionRequestDto;
import com.psh10066.survey.survey_management.application.port.in.command.RegisterSurveyCommand;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record RegisterSurveyRequest(
    @Schema(description = "설문조사 이름", requiredMode = Schema.RequiredMode.REQUIRED)
    String name,

    @Schema(description = "설문조사 설명", requiredMode = Schema.RequiredMode.REQUIRED)
    String description,

    @Schema(description = "설문받을 항목", requiredMode = Schema.RequiredMode.REQUIRED)
    List<SurveyQuestionRequestDto> questions
) {

    public RegisterSurveyCommand toCommand() {
        return RegisterSurveyRequestMapper.INSTANCE.toCommand(this);
    }
}
