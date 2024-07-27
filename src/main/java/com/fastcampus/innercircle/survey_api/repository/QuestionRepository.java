package com.fastcampus.innercircle.survey_api.repository;

import com.fastcampus.innercircle.survey_api.domain.entity.Question;
import com.fastcampus.innercircle.survey_api.domain.entity.SurveyForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByFormAndIsRemovedFalse(SurveyForm form);
}
