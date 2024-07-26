package com.innercircle.onboardingservey.infrastructure.repository;

import com.innercircle.onboardingservey.domain.model.Survey;
import com.innercircle.onboardingservey.domain.model.SurveyVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyVersionRepository extends JpaRepository<SurveyVersion, Long> {
    List<SurveyVersion> findBySurvey(Survey survey);
}
