package com.innercircle.survey.repository;

import com.innercircle.survey.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
