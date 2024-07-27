package com.innercircle.onboardingservey.infrastructure.repository;

import com.innercircle.onboardingservey.domain.model.entity.Survey;
import com.innercircle.onboardingservey.domain.model.entity.SurveyVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SurveyVersionRepository extends JpaRepository<SurveyVersion, Long> {
    List<SurveyVersion> findBySurvey(Survey survey);
    Optional<SurveyVersion> findById(Long surveyVersionId);
}
