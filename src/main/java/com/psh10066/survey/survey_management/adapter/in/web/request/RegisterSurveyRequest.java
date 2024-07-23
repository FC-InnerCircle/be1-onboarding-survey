package com.psh10066.survey.survey_management.adapter.in.web.request;

import com.psh10066.survey.survey_management.adapter.in.web.request.dto.SurveyQuestionRequestDto;
import com.psh10066.survey.survey_management.application.port.in.command.RegisterSurveyCommand;

import java.util.List;

public record RegisterSurveyRequest(
    String name,
    String description,
    List<SurveyQuestionRequestDto> questions
) {

    public RegisterSurveyCommand toCommand() {
        return RegisterSurveyRequestMapper.INSTANCE.toCommand(this);
    }
}
