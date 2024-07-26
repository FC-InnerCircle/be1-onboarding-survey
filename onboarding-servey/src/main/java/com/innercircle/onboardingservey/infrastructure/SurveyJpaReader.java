package com.innercircle.onboardingservey.infrastructure;

import com.innercircle.onboardingservey.domain.SurveyReader;
import com.innercircle.onboardingservey.domain.model.Survey;
import com.innercircle.onboardingservey.domain.model.SurveyVersion;
import com.innercircle.onboardingservey.infrastructure.repository.SurveyRepository;
import com.innercircle.onboardingservey.infrastructure.repository.SurveyVersionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SurveyJpaReader implements SurveyReader {
    private final SurveyRepository surveyRepository;
    private final SurveyVersionRepository surveyVersionRepository;

    @Override
    public Survey getSurveyBySurveyId(Long surveyId) {
        return surveyRepository.findById(surveyId)
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<SurveyVersion> getSurveyVersionAllBySurveyId(Survey survey) {
        return surveyVersionRepository.findBySurvey(survey);
    }
}
