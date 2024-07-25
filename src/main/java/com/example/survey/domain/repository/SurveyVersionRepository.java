package com.example.survey.domain.repository;

import com.example.survey.domain.model.SurveyVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyVersionRepository extends JpaRepository<SurveyVersion, Long> {
    List<SurveyVersion> findBySurveyId(Long surveyId);
    SurveyVersion findBySurveyIdAndVersion(Long surveyId, int version);
}
