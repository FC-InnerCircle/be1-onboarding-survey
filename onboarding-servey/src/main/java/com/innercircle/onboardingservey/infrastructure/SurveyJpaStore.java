package com.innercircle.onboardingservey.infrastructure;

import com.innercircle.onboardingservey.domain.SurveyStore;
import com.innercircle.onboardingservey.domain.model.Survey;
import com.innercircle.onboardingservey.domain.model.SurveyCommand;
import com.innercircle.onboardingservey.domain.model.SurveyResult;
import com.innercircle.onboardingservey.infrastructure.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SurveyJpaStore implements SurveyStore {
    private final SurveyRepository surveyRepository;

    @Override
    public Survey store(Survey survey) {
        return surveyRepository.save(survey);
    }
}
