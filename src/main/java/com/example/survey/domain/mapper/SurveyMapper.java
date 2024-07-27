package com.example.survey.domain.mapper;

import com.example.survey.adapter.dto.SurveyDto;
import com.example.survey.adapter.dto.SurveyItemDto;
import com.example.survey.domain.model.Survey;
import com.example.survey.domain.model.SurveyItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class SurveyMapper {

    public static Survey surveyDtoToSurvey(SurveyDto surveyDto) {
        if (Objects.isNull(surveyDto)) {
            return null;
        }

        return Survey.builder()
            .name(surveyDto.getName())
            .description(surveyDto.getDescription())
            .surveyItems(Optional.ofNullable(surveyDto.getItems())
                .map(items -> items.stream()
                    .map(SurveyMapper::surveyItemDtoToSurveyItem)
                    .collect(Collectors.toList()))
                .orElseGet(ArrayList::new))
            .build();
    }

    public static SurveyDto surveyToSurveyDto(Survey survey) {
        if (Objects.isNull(survey)) {
            return null;
        }

        return SurveyDto.builder()
            .surveyId(survey.getId())
            .name(survey.getName())
            .description(survey.getDescription())
            .items(Optional.ofNullable(survey.getSurveyItems())
                .map(items -> items.stream()
                    .map(SurveyMapper::surveyItemToSurveyItemDto)
                    .collect(Collectors.toList()))
                .orElseGet(ArrayList::new))
            .build();
    }

    public static SurveyItem surveyItemDtoToSurveyItem(SurveyItemDto surveyItemDto) {
        if (Objects.isNull(surveyItemDto)) {
            return null;
        }

        return SurveyItem.builder()
            .name(surveyItemDto.getName())
            .description(surveyItemDto.getDescription())
            .inputType(surveyItemDto.getInputType())
            .required(surveyItemDto.isRequired())
            .options(Optional.ofNullable(surveyItemDto.getOptions())
                .map(ArrayList::new)
                .orElseGet(ArrayList::new))
            .build();
    }

    public static List<SurveyItem> surveyItemDtosToSurveyItems(List<SurveyItemDto> itemDtos) {
        if (Objects.isNull(itemDtos)) {
            return new ArrayList<>();
        }

        return itemDtos.stream()
            .map(SurveyMapper::surveyItemDtoToSurveyItem)
            .collect(Collectors.toList());
    }

    public static SurveyItemDto surveyItemToSurveyItemDto(SurveyItem surveyItem) {
        if (Objects.isNull(surveyItem)) {
            return null;
        }

        return SurveyItemDto.builder()
            .id(surveyItem.getId())
            .name(surveyItem.getName())
            .description(surveyItem.getDescription())
            .inputType(surveyItem.getInputType())
            .required(surveyItem.isRequired())
            .options(Optional.ofNullable(surveyItem.getOptions())
                .map(ArrayList::new)
                .orElseGet(ArrayList::new))
            .build();
    }
}
