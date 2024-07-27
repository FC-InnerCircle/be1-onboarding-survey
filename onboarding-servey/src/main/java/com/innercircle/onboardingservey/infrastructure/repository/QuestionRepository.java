package com.innercircle.onboardingservey.infrastructure.repository;

import com.innercircle.onboardingservey.domain.model.entity.Question;

import com.innercircle.onboardingservey.domain.model.entity.SurveyVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySurveyVersion(SurveyVersion surveyVersion);
}
