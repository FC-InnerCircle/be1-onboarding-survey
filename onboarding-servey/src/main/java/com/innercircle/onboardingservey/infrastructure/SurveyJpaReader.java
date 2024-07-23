package com.innercircle.onboardingservey.infrastructure;

import com.innercircle.onboardingservey.domain.SurveyReader;
import com.innercircle.onboardingservey.domain.model.Survey;
import com.innercircle.onboardingservey.infrastructure.repository.SurveyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SurveyJpaReader implements SurveyReader {
    private final SurveyRepository surveyRepository;

    @Override
    public Survey getBySurveyId(Long surveyId) {
        return surveyRepository.findById(surveyId)
            .orElseThrow(EntityNotFoundException::new);
    }
}
