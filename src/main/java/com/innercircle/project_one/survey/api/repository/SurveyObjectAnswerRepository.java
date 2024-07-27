package com.innercircle.project_one.survey.api.repository;

import com.innercircle.project_one.survey.domain.SurveyObjectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyObjectAnswerRepository extends JpaRepository<SurveyObjectAnswer, Long> {
}
