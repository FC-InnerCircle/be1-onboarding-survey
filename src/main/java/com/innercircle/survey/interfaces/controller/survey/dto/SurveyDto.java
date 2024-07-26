package com.innercircle.survey.interfaces.controller.survey.dto;

import com.innercircle.survey.domain.survey.Survey;
import com.innercircle.survey.domain.survey.SurveyItem;
import com.innercircle.survey.domain.survey.command.SurveyCommand;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

public class SurveyDto {

    @Builder(toBuilder = true)
    public record Request(
            @NotBlank String surveyName,
            String surveyDescription,
            @Validated List<SurveyItemDto> surveyItems) {

        public SurveyCommand.Create toCreateCommand() {

            return SurveyCommand.Create.builder()
                    .surveyName(surveyName)
                    .surveyDescription(surveyDescription)
                    .surveyItems(
                            (surveyItems != null) ? surveyItems.stream()
                                    .map(surveyItemDto -> {
                                        List<SurveyCommand.Create.GetSurveyItem.GetSelectOption> selectOptions = null;
                                        if (surveyItemDto.selectOptions != null) {
                                            selectOptions = surveyItemDto.selectOptions.stream()
                                                    .map(selectOptionDto -> SurveyCommand.Create.GetSurveyItem.GetSelectOption
                                                            .builder()
                                                            .name(selectOptionDto.name())
                                                            .build())
                                                    .toList();
                                        }
                                        return SurveyCommand.Create.GetSurveyItem.builder()
                                                .surveyItemName(surveyItemDto.surveyItemName)
                                                .surveyItemDescription(surveyItemDto.surveyItemDescription)
                                                .inputFormat(surveyItemDto.inputFormat)
                                                .itemRequired(surveyItemDto.itemRequired)
                                                .selectOptions(selectOptions)
                                                .build();
                                    }).toList() : new ArrayList<>()
                    )
                    .build();
        }

        @Builder(toBuilder = true)
        public record SurveyItemDto(
                @NotBlank String surveyItemName,
                String surveyItemDescription,
                @NotBlank SurveyItem.ItemInputFormat inputFormat,
                @NotBlank Boolean itemRequired,
                List<SelectOptionDto> selectOptions


        ) {
            @Builder(toBuilder = true)
            public record SelectOptionDto(String name) {

            }
        }
    }

    @Builder(toBuilder = true)
    public record Response(
            Long surveyId,
            String surveyName,
            String surveyDescription,
            List<Response.SurveyItemDto> surveyItems) {

        @Builder(toBuilder = true)
        public record SurveyItemDto(
                Long surveyItemId,
                String surveyItemName,
                String surveyItemDescription,
                SurveyItem.ItemInputFormat inputFormat,
                Boolean itemRequired,
                List<Response.SurveyItemDto.SelectOptionDto> selectOptions


        ) {
            @Builder(toBuilder = true)
            public record SelectOptionDto(
                    Long SurveyOptionId,
                    String name) {
            }
        }

        public static SurveyDto.Response of(Survey survey) {
            return Response.builder()
                    .surveyId(survey.getSurveyId())
                    .surveyName(survey.getSurveyName())
                    .surveyDescription(survey.getSurveyDescription())
                    .surveyItems(
                            survey.getSurveyItems().stream()
                                    .map(surveyItem -> {
                                        List<SurveyItemDto.SelectOptionDto> selectOptions = null;
                                        if (surveyItem.getSelectOptions() != null) {
                                            selectOptions = surveyItem.getSelectOptions().stream()
                                                    .map(selectOption -> SurveyItemDto.SelectOptionDto.builder()
                                                            .SurveyOptionId(selectOption.getSelectOptionId())
                                                            .name(selectOption.getSelectItemName())
                                                            .build())
                                                    .toList();
                                        }
                                        return SurveyItemDto.builder()
                                                .surveyItemId(surveyItem.getSurveyItemId())
                                                .surveyItemName(surveyItem.getSurveyItemName())
                                                .surveyItemDescription(surveyItem.getSurveyItemDescription())
                                                .itemRequired(surveyItem.isItemRequired())
                                                .inputFormat(surveyItem.getInputFormat())
                                                .selectOptions(selectOptions)
                                                .build();
                                    })
                                    .toList()
                    )
                    .build();
        }
    }
}
