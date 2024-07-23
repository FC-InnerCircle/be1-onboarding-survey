package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.Question;
import com.innercircle.onboardingservey.domain.model.Survey;

import java.util.List;

public interface QuestionReader {
    List<Question> findBySurvey(Survey survey);
}
