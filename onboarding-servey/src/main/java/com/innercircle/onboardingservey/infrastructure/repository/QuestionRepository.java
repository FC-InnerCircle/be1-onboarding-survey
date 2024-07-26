package com.innercircle.onboardingservey.infrastructure.repository;

import com.innercircle.onboardingservey.domain.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
