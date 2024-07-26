package com.psh10066.survey.survey_answer.adapter.in.web.request;

import com.psh10066.survey.survey_answer.application.port.in.command.RegisterSurveyAnswerCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RegisterSurveyAnswerRequestMapper {

    RegisterSurveyAnswerRequestMapper INSTANCE = Mappers.getMapper(RegisterSurveyAnswerRequestMapper.class);

    RegisterSurveyAnswerCommand toCommand(RegisterSurveyAnswerRequest request);
}
