package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.Survey;
import com.innercircle.onboardingservey.domain.model.SurveyVersion;

public interface SurveyStore {
    Survey store(Survey survey);
    SurveyVersion store(SurveyVersion surveyVersion);
}
