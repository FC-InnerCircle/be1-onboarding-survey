package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.entity.Survey;
import com.innercircle.onboardingservey.domain.model.entity.SurveyVersion;

public interface SurveyStore {
    Survey store(Survey survey);
    SurveyVersion store(SurveyVersion surveyVersion);
}
