package com.psh10066.survey.survey_answer.adapter.in.web.request;

import com.psh10066.survey.survey_answer.adapter.in.web.request.dto.SurveyQuestionAnswerRequestDto;
import com.psh10066.survey.survey_answer.application.port.in.command.RegisterSurveyAnswerCommand;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.UUID;

public record RegisterSurveyAnswerRequest(

    @Schema(description = "설문조사 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    UUID surveyId,

    @Schema(description = "설문조사 버전", requiredMode = Schema.RequiredMode.REQUIRED)
    Long surveyVersion,

    @Schema(description = "응답 항목", requiredMode = Schema.RequiredMode.REQUIRED)
    List<SurveyQuestionAnswerRequestDto> answers
) {

    public RegisterSurveyAnswerCommand toCommand() {
        return RegisterSurveyAnswerRequestMapper.INSTANCE.toCommand(this);
    }
}
