package com.psh10066.survey.survey_answer.adapter.out.persistence;

import com.psh10066.survey.survey_answer.application.port.out.RegisterSurveyAnswerPort;
import com.psh10066.survey.survey_answer.domain.SurveyAnswer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@RequiredArgsConstructor
public class SurveyAnswerPersistenceAdapter implements RegisterSurveyAnswerPort {

    private final SurveyAnswerJpaRepository surveyAnswerJpaRepository;

    @Override
    public Long register(SurveyAnswer surveyAnswer) {
        return surveyAnswerJpaRepository.save(SurveyAnswerJpaEntity.from(surveyAnswer)).getId();
    }
}
