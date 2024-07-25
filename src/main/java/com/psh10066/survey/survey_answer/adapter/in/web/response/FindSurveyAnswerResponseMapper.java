package com.psh10066.survey.survey_answer.adapter.in.web.response;

import com.psh10066.survey.survey_answer.adapter.in.web.response.dto.FindSurveyAnswerResponseDto;
import com.psh10066.survey.survey_answer.domain.SurveyAnswer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FindSurveyAnswerResponseMapper {

    FindSurveyAnswerResponseMapper INSTANCE = Mappers.getMapper(FindSurveyAnswerResponseMapper.class);

    List<FindSurveyAnswerResponseDto> toDto(List<SurveyAnswer> surveyAnswers);
}
