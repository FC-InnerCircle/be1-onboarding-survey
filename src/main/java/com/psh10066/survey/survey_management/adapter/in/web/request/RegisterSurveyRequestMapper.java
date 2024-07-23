package com.psh10066.survey.survey_management.adapter.in.web.request;

import com.psh10066.survey.survey_management.application.port.in.command.RegisterSurveyCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterSurveyRequestMapper {

    RegisterSurveyRequestMapper INSTANCE = Mappers.getMapper(RegisterSurveyRequestMapper.class);

    RegisterSurveyCommand toCommand(RegisterSurveyRequest request);
}
