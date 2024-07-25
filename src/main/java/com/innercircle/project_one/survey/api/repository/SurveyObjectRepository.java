package com.innercircle.project_one.survey.api.repository;

import com.innercircle.project_one.survey.domain.SurveyObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyObjectRepository extends JpaRepository<SurveyObject, Long> {
}
