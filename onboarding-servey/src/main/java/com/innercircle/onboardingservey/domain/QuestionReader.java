package com.innercircle.onboardingservey.domain;


import com.innercircle.onboardingservey.domain.model.entity.Question;
import com.innercircle.onboardingservey.domain.model.entity.SurveyVersion;

import java.util.List;

public interface QuestionReader {
    List<Question> findQuestionsBySurveyVersion(SurveyVersion surveyVersion);
}
