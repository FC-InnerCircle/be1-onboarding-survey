package com.innercircle.project_one.survey.api.repository;

import com.innercircle.project_one.survey.domain.SurveyObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyObjectRepository extends JpaRepository<SurveyObject, Long> {
    Optional<List<SurveyObject>> findBySurveyIdAndSurveyVersionOrderByElementOrder(Long surveyId, Long surveyVersion);
    List<SurveyObject>  findBySurveyId(Long surveyId);
}

