package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.Survey;

public interface SurveyStore {
    Survey store(Survey survey);
}
