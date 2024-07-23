package com.psh10066.survey.survey_management.application.port.in;

import com.psh10066.survey.survey_management.application.port.in.command.RegisterSurveyCommand;
import com.psh10066.survey.survey_management.domain.Survey;

public interface RegisterSurveyUseCase {

    Survey.SurveyId registerSurvey(RegisterSurveyCommand command);
}
