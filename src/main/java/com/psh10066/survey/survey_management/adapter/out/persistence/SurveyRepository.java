package com.psh10066.survey.survey_management.adapter.out.persistence;

import com.psh10066.survey.survey_management.application.port.out.RegisterSurveyPort;
import com.psh10066.survey.survey_management.domain.Survey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class SurveyRepository implements RegisterSurveyPort {

    private final SurveyJpaRepository surveyJpaRepository;
    private final SurveyFormJpaRepository surveyFormJpaRepository;

    @Override
    public Survey.SurveyId register(Survey survey) {
        surveyJpaRepository.save(SurveyJpaEntity.from(survey));
        surveyFormJpaRepository.save(
            SurveyFormJpaEntity.from(
                survey.getId(),
                survey.getForm()
            )
        );
        return survey.getId();
    }
}
