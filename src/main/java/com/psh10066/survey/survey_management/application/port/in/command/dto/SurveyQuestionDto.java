package com.psh10066.survey.survey_management.application.port.in.command.dto;

import com.psh10066.survey.survey_management.domain.SurveyQuestion;
import com.psh10066.survey.survey_management.domain.SurveyQuestionType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record SurveyQuestionDto(
    @NotBlank
    String name,

    @NotBlank
    String description,

    @NotNull
    SurveyQuestionType type,

    @Valid
    List<@NotBlank String> selectInputs,

    @NotNull
    Boolean required
) {
    public SurveyQuestion toModel() {
        return SurveyQuestion.create(
            this.name,
            this.description,
            this.type,
            this.selectInputs,
            this.required
        );
    }
}
