package com.inner_circle.survey.repository;

import com.inner_circle.survey.entity.request.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
