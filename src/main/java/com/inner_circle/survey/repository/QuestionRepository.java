package com.inner_circle.survey.repository;

import com.inner_circle.survey.entity.request.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
