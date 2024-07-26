package com.psh10066.survey.survey_answer.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SurveyAnswerJpaRepository extends JpaRepository<SurveyAnswerJpaEntity, Long> {

    List<SurveyAnswerJpaEntity> findBySurveyId(UUID surveyId);
}
