package com.psh10066.survey.survey_management.application.port.in.command.dto;

import com.psh10066.survey.survey_management.domain.SurveySelectInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SurveySelectInputDto(
    @NotNull
    Integer inputValue,

    @NotBlank
    String text
) {

    public SurveySelectInput toModel() {
        return new SurveySelectInput(this.inputValue, this.text);
    }
}
