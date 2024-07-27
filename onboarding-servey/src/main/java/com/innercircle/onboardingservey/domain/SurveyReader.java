package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.entity.Survey;
import com.innercircle.onboardingservey.domain.model.entity.SurveyVersion;

import java.util.List;

public interface SurveyReader {

    Survey getSurveyBySurveyId(Long surveyId);
    List<SurveyVersion> getSurveyVersionAllBySurveyId(Survey survey);
    SurveyVersion getSurveyVersionBySurveyVersionId(Long surveyVersionId);
}
