package com.inner_circle.survey.repository;

import com.inner_circle.survey.entity.request.Survey;
import com.inner_circle.survey.entity.response.Respondent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RespondentRepository extends JpaRepository<Respondent, Long> {

  List<Respondent> findBySurvey(Survey survey);
}
