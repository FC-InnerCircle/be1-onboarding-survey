package com.innercircle.onboardingservey.infrastructure;

import com.innercircle.onboardingservey.domain.SurveyStore;
import com.innercircle.onboardingservey.domain.model.entity.Survey;
import com.innercircle.onboardingservey.domain.model.entity.SurveyVersion;
import com.innercircle.onboardingservey.infrastructure.repository.SurveyRepository;
import com.innercircle.onboardingservey.infrastructure.repository.SurveyVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SurveyJpaStore implements SurveyStore {
    private final SurveyRepository surveyRepository;
    private final SurveyVersionRepository surveyVersionRepository;

    @Override
    public Survey store(Survey survey) {
        return surveyRepository.save(survey);
    }

    @Override
    public SurveyVersion store(SurveyVersion surveyVersion) {
        return surveyVersionRepository.save(surveyVersion);
    }
}
