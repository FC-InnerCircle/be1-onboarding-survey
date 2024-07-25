package com.example.survey.domain.mapper;

import com.example.survey.adapter.dto.SurveyDto;
import com.example.survey.adapter.dto.SurveyItemDto;
import com.example.survey.domain.model.Survey;
import com.example.survey.domain.model.SurveyItem;

import java.util.stream.Collectors;

public class SurveyMapper {

    public static Survey surveyDtoToSurvey(SurveyDto surveyDto) {
        return Survey.builder()
            .name(surveyDto.getName())
            .description(surveyDto.getDescription())
            .items(surveyDto.getItems().stream()
                .map(SurveyMapper::surveyItemDtoToSurveyItem)
                .collect(Collectors.toList()))
            .build();
    }

    public static SurveyDto surveyToSurveyDto(Survey survey) {
        return SurveyDto.builder()
            .surveyId(survey.getId())
            .name(survey.getName())
            .description(survey.getDescription())
            .items(survey.getItems().stream()
                .map(SurveyMapper::surveyItemToSurveyItemDto)
                .collect(Collectors.toList()))
            .build();
    }

    public static SurveyItem surveyItemDtoToSurveyItem(SurveyItemDto surveyItemDto) {
        return SurveyItem.builder()
            .name(surveyItemDto.getName())
            .description(surveyItemDto.getDescription())
            .inputType(surveyItemDto.getInputType())
            .required(surveyItemDto.isRequired())
            .options(surveyItemDto.getOptions())
            .build();
    }

    public static SurveyItemDto surveyItemToSurveyItemDto(SurveyItem surveyItem) {
        return SurveyItemDto.builder()
            .id(surveyItem.getId())
            .name(surveyItem.getName())
            .description(surveyItem.getDescription())
            .inputType(surveyItem.getInputType())
            .required(surveyItem.isRequired())
            .options(surveyItem.getOptions())
            .build();
    }
}
