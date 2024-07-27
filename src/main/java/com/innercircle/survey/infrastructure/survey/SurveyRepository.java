package com.innercircle.survey.infrastructure.survey;

import com.innercircle.survey.domain.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
