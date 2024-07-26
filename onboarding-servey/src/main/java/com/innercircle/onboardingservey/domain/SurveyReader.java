package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.Survey;
import com.innercircle.onboardingservey.domain.model.SurveyVersion;

import java.util.List;

public interface SurveyReader {

    Survey getSurveyBySurveyId(Long surveyId);
    List<SurveyVersion> getSurveyVersionAllBySurveyId(Survey survey);
}
