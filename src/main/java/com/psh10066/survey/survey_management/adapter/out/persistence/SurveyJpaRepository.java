package com.psh10066.survey.survey_management.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SurveyJpaRepository extends JpaRepository<SurveyJpaEntity, UUID> {
}
