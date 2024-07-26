package com.innercircle.survey.infrastruture.survey;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SelectOptionJpaRepository extends JpaRepository<SelectOptionEntity, Long> {
    List<SelectOptionEntity> findAllBySurveyItem_surveyItemId(Long surveyItemId);
}
