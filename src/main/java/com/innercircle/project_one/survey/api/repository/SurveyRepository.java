package com.innercircle.project_one.survey.api.repository;

import com.innercircle.project_one.survey.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    Optional<Survey> findById(Long surveyId);
}