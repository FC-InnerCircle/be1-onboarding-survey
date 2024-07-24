package com.psh10066.survey.survey_management.application.port.out;

import com.psh10066.survey.survey_management.domain.Survey;

public interface ModifySurveyPort {
    Survey.SurveyId modify(Survey survey);
}
