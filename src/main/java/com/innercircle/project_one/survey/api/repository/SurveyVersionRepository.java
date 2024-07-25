package com.innercircle.project_one.survey.api.repository;

import com.innercircle.project_one.survey.domain.SurveyVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyVersionRepository extends JpaRepository<SurveyVersion, Long> {
}
