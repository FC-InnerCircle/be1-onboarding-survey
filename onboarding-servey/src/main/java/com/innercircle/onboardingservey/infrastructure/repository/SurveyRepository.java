package com.innercircle.onboardingservey.infrastructure.repository;

import com.innercircle.onboardingservey.domain.model.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
