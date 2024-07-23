package com.inner_circle.survey.repository;

import com.inner_circle.survey.entity.request.Question;
import com.inner_circle.survey.entity.request.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

  List<Question> findBySurveyAndLatestTrue(Survey survey);
}
