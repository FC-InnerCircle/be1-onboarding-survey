package com.psh10066.survey.survey_management.application.port.in;

import com.psh10066.survey.survey_management.application.port.in.command.ModifySurveyCommand;
import com.psh10066.survey.survey_management.domain.Survey;

public interface ModifySurveyUseCase {

    Survey.SurveyId modifySurvey(ModifySurveyCommand command);
}
