package com.innercircle.onboardingservey.infrastructure;

import com.innercircle.onboardingservey.domain.QuestionReader;
import com.innercircle.onboardingservey.domain.model.entity.Question;
import com.innercircle.onboardingservey.domain.model.entity.SurveyVersion;
import com.innercircle.onboardingservey.infrastructure.repository.QuestionRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuestionJpaReader implements QuestionReader {
    private final QuestionRepository questionRepository;

    @Override
    public List<Question> findQuestionsBySurveyVersion(SurveyVersion surveyVersion) {
        return questionRepository.findBySurveyVersion(surveyVersion);
    }
}
