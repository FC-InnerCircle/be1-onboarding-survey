package com.psh10066.survey.survey_answer.application.port.in;

import com.psh10066.survey.survey_answer.application.port.in.command.RegisterSurveyAnswerCommand;

public interface RegisterSurveyAnswerUseCase {

    Long register(RegisterSurveyAnswerCommand command);
}
