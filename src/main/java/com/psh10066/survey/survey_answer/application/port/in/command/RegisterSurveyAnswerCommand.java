package com.psh10066.survey.survey_answer.application.port.in.command;

import com.psh10066.survey.common.application.port.in.command.SelfValidating;
import com.psh10066.survey.survey_answer.application.port.in.command.dto.SurveyQuestionAnswerCommandDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class RegisterSurveyAnswerCommand extends SelfValidating<RegisterSurveyAnswerCommand> {

    @NotNull
    private final UUID surveyId;

    @NotNull
    private final Long surveyVersion;

    @NotNull
    @NotEmpty
    @Valid
    private final List<SurveyQuestionAnswerCommandDto> answers;

    public RegisterSurveyAnswerCommand(UUID surveyId, Long surveyVersion, List<SurveyQuestionAnswerCommandDto> answers) {
        this.surveyId = surveyId;
        this.surveyVersion = surveyVersion;
        this.answers = answers;
        super.validateSelf();
    }
}
