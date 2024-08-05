package com.innercircle.project_one.survey.api.repository;

import com.innercircle.project_one.survey.domain.ElementSurveyObjectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ElementSurveyObjectAnswerRepository extends JpaRepository<ElementSurveyObjectAnswer, Long> {
    @Query("SELECT s FROM ElementSurveyObjectAnswer s WHERE s.surveyObject.id = :surveyId")
    List<ElementSurveyObjectAnswer> findAllBySurveyObjectId(Long surveyId);
}
