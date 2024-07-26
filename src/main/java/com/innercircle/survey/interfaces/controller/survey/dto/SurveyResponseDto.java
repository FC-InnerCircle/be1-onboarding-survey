package com.innercircle.survey.interfaces.controller.survey.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.innercircle.survey.domain.survey.SurveyItem;
import com.innercircle.survey.domain.survey.SurveyResponse;
import com.innercircle.survey.domain.survey.command.SurveyResponseCommand;
import com.innercircle.survey.domain.survey.command.SurveyResponseCommand.Create.GetSurveyResponses.GetAnswerOption;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static com.innercircle.survey.domain.survey.command.SurveyResponseCommand.Create.GetSurveyResponses;

public class SurveyResponseDto {

    @Builder(toBuilder = true)
    public record Request(
            @NotNull Long surveyId,
            @Validated List<SurveyResponseItemDto> surveyItems) {

        public SurveyResponseCommand.Create toCreateCommand() {
            return SurveyResponseCommand.Create.builder()
                    .surveyId(surveyId)
                    .surveyResponses(
                            surveyItems.stream()
                                    .map(itemDto ->
                                            GetSurveyResponses.builder()
                                                    .surveyItemId(itemDto.surveyItemId)
                                                    .inputFormat(itemDto.inputFormat)
                                                    .answerText(itemDto.answerText)
                                                    .answerOptions(
                                                            itemDto.answerOptions != null ?
                                                                    itemDto.answerOptions.stream().map(
                                                                            answerOption -> GetAnswerOption.builder()
                                                                                    .name(answerOption.name)
                                                                                    .build()
                                                                    ).toList() : null
                                                    )
                                                    .build()
                                    )
                                    .toList()
                    )
                    .build();
        }

        @Builder(toBuilder = true)
        public record SurveyResponseItemDto(
                @NotNull Long surveyItemId,
                @NotBlank SurveyItem.ItemInputFormat inputFormat,
                String answerText,
                List<AnswerOption> answerOptions) {

            @Builder(toBuilder = true)
            public record AnswerOption(String name) {
            }
        }
    }

    @Builder(toBuilder = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record Response(
            Long surveyResponseId,
            Long surveyId,
            Response.SurveyResponseItemDto surveyItem) {

        @Builder(toBuilder = true)
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public record SurveyResponseItemDto(
                String responseShortValue,
                String responseLongValue,
                List<Response.SurveyResponseItemDto.ResponseAnswer> responseAnswers) {

            @Builder(toBuilder = true)
            @JsonInclude(JsonInclude.Include.NON_NULL)
            public record ResponseAnswer(
                    Long responseDetailId,
                    String responseValue) {
            }
        }

        public static SurveyResponseDto.Response of(SurveyResponse surveyResponse) {
            return Response.builder()
                    .surveyResponseId(surveyResponse.getSurveyResponseId())
                    .surveyId(surveyResponse.getSurveyId())
                    .surveyItem(SurveyResponseItemDto.builder()
                            .responseShortValue(surveyResponse.getResponseShortValue())
                            .responseLongValue(surveyResponse.getResponseLongValue())
                            .responseAnswers(
                                    surveyResponse.getSurveyResponseDetails() != null ?
                                            surveyResponse.getSurveyResponseDetails()
                                                    .stream().map(surveyResponseDetail -> SurveyResponseItemDto.ResponseAnswer.builder()
                                                            .responseDetailId(surveyResponseDetail.getResponseDetailId())
                                                            .responseValue(surveyResponseDetail.getResponseValue())
                                                            .build()
                                                    ).toList() : null)
                            .build())
                    .build();
        }
    }

}

