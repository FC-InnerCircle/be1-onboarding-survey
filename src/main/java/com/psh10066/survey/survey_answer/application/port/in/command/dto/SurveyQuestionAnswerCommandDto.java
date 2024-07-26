package com.psh10066.survey.survey_answer.application.port.in.command.dto;

import com.psh10066.survey.survey_answer.domain.SurveyQuestionAnswer;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SurveyQuestionAnswerCommandDto(
    @NotNull
    Integer surveyQuestionId,

    String textAnswer,

    List<Integer> selectInputValue
) {
    public SurveyQuestionAnswer toModel() {
        return new SurveyQuestionAnswer(surveyQuestionId, textAnswer, selectInputValue);
    }
}
