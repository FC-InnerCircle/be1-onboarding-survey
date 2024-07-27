package com.fastcampus.innercircle.survey_api.repository;

import com.fastcampus.innercircle.survey_api.domain.entity.SurveyForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyFormRepository extends JpaRepository<SurveyForm, Long> {
}
