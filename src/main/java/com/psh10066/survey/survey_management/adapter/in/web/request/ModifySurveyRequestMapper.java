package com.psh10066.survey.survey_management.adapter.in.web.request;

import com.psh10066.survey.survey_management.application.port.in.command.ModifySurveyCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ModifySurveyRequestMapper {

    ModifySurveyRequestMapper INSTANCE = Mappers.getMapper(ModifySurveyRequestMapper.class);

    ModifySurveyCommand toCommand(ModifySurveyRequest request);
}
