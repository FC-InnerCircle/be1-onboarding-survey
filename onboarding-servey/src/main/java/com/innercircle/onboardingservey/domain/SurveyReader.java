package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.Survey;

public interface SurveyReader {

    Survey getBySurveyId(Long surveyId);
}
