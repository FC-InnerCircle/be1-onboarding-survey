package com.innercircle.project_one.survey.api.repository;

import com.innercircle.project_one.survey.domain.StringSurveyObjectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StringSurveyObjectAnswerRepository extends JpaRepository<StringSurveyObjectAnswer, Long> {
    List<StringSurveyObjectAnswer> findAllBySurveyObjectId(Long surveyId);
    @Query("SELECT s FROM StringSurveyObjectAnswer s WHERE s.surveyObject.id = :surveyId")
    StringSurveyObjectAnswer findBySurveyObjectId(Long surveyId);
}
