package com.innercircle.project_one.survey.api.repository;

import com.innercircle.project_one.survey.domain.SurveyObjectAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyObjectAnswerRepository extends JpaRepository<SurveyObjectAnswer, Long> {

    List<SurveyObjectAnswer> findAllBySurveyObjectId(Long id);

}
