package com.psh10066.survey.survey_answer.adapter.out.persistence;

import com.psh10066.survey.survey_answer.application.port.out.FindSurveyAnswerPort;
import com.psh10066.survey.survey_answer.application.port.out.RegisterSurveyAnswerPort;
import com.psh10066.survey.survey_answer.domain.SurveyAnswer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
@RequiredArgsConstructor
public class SurveyAnswerPersistenceAdapter implements RegisterSurveyAnswerPort, FindSurveyAnswerPort {

    private final SurveyAnswerJpaRepository surveyAnswerJpaRepository;

    @Override
    public Long register(SurveyAnswer surveyAnswer) {
        return surveyAnswerJpaRepository.save(SurveyAnswerJpaEntity.from(surveyAnswer)).getId();
    }

    @Override
    public List<SurveyAnswer> findBySurveyId(UUID surveyId) {
        return surveyAnswerJpaRepository.findBySurveyId(surveyId).stream()
            .map(SurveyAnswerJpaEntity::toModel)
            .toList();
    }
}
