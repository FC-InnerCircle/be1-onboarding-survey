package com.innercircle.onboardingservey.infrastructure.repository;

import com.innercircle.onboardingservey.domain.model.entity.QuestionAnswerDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerDetailRepository extends JpaRepository<QuestionAnswerDetail, Long> {
}
