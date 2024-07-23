package com.psh10066.survey.survey_management.application.port.in.command;

import com.psh10066.survey.common.application.port.in.command.SelfValidating;
import com.psh10066.survey.survey_management.application.port.in.command.dto.SurveyQuestionDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class RegisterSurveyCommand extends SelfValidating<RegisterSurveyCommand> {

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotNull
    @NotEmpty
    @Valid
    private final List<SurveyQuestionDto> questions;

    public RegisterSurveyCommand(String name, String description, List<SurveyQuestionDto> questions) {
        this.name = name;
        this.description = description;
        this.questions = questions;
        super.validateSelf();
    }
}
