package lshh.be1onboardingsurvey.survey.domain.component;

import lshh.be1onboardingsurvey.survey.domain.entity.SurveyResponse;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;

import java.util.Optional;

public interface SurveyResponseBuffer {
    Optional<SurveyResponse> findResponseById(Long responseId);

    Result<?> save(SurveyResponse response);
}
