package com.innercircle.onboardingservey.infrastructure.repository;

import com.innercircle.onboardingservey.domain.model.entity.Answer;
import com.innercircle.onboardingservey.domain.model.entity.Survey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAnswersBySurvey(Survey survey);
}
