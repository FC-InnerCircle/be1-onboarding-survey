package com.psh10066.survey.survey_management.application.port.out;

import com.psh10066.survey.survey_management.domain.Survey;

public interface FindSurveyPort {
    Long getLatestVersion(Survey.SurveyId surveyId);
}
