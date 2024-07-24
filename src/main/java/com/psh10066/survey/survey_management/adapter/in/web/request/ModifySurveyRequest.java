package com.psh10066.survey.survey_management.adapter.in.web.request;

import com.psh10066.survey.survey_management.adapter.in.web.request.dto.SurveyQuestionRequestDto;
import com.psh10066.survey.survey_management.application.port.in.command.ModifySurveyCommand;

import java.util.List;
import java.util.UUID;

public record ModifySurveyRequest(
    UUID id,
    String name,
    String description,
    List<SurveyQuestionRequestDto> questions
) {

    public ModifySurveyCommand toCommand() {
        return ModifySurveyRequestMapper.INSTANCE.toCommand(this);
    }
}
