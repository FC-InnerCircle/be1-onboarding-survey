package com.psh10066.survey.survey_management.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SurveyFormJpaRepository extends JpaRepository<SurveyFormJpaEntity, Long> {
    Optional<SurveyFormJpaEntity> findBySurveyIdAndVersion(UUID surveyId, Long version);
}
