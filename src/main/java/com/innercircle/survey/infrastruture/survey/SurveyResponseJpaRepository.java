package com.innercircle.survey.infrastruture.survey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyResponseJpaRepository extends JpaRepository<SurveyResponseEntity, Long> {
}
