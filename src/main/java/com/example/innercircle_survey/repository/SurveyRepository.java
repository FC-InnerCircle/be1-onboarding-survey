package com.example.innercircle_survey.repository;

import com.example.innercircle_survey.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
