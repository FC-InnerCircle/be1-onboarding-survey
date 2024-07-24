package com.psh10066.survey.survey_management.application.port.in.command;

import com.psh10066.survey.common.application.port.in.command.SelfValidating;
import com.psh10066.survey.survey_management.application.port.in.command.dto.SurveyQuestionDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class ModifySurveyCommand extends SelfValidating<ModifySurveyCommand> {

    @NotNull
    private final UUID id;

    @NotBlank
    private final String name;

    @NotBlank
    private final String description;

    @NotNull
    @NotEmpty
    @Valid
    private final List<SurveyQuestionDto> questions;

    public ModifySurveyCommand(UUID id, String name, String description, List<SurveyQuestionDto> questions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.questions = questions;
        super.validateSelf();
    }
}
