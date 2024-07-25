package com.psh10066.survey.survey_management.adapter.out.persistence;

import com.psh10066.survey.survey_management.application.port.out.FindSurveyPort;
import com.psh10066.survey.survey_management.application.port.out.ModifySurveyPort;
import com.psh10066.survey.survey_management.application.port.out.RegisterSurveyPort;
import com.psh10066.survey.survey_management.domain.Survey;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class SurveyPersistenceAdapter implements RegisterSurveyPort, ModifySurveyPort, FindSurveyPort {

    private final SurveyJpaRepository surveyJpaRepository;
    private final SurveyFormJpaRepository surveyFormJpaRepository;

    private Survey.SurveyId save(Survey survey) {
        surveyJpaRepository.save(SurveyJpaEntity.from(survey));
        surveyFormJpaRepository.save(
            SurveyFormJpaEntity.from(
                survey.getId(),
                survey.getForm()
            )
        );
        return survey.getId();
    }

    @Override
    public Survey.SurveyId register(Survey survey) {
        return this.save(survey);
    }

    @Override
    public Survey.SurveyId modify(Survey survey) {
        return this.save(survey);
    }

    @Override
    public Long getLatestVersion(Survey.SurveyId surveyId) {
        SurveyJpaEntity entity = surveyJpaRepository.findById(surveyId.value())
            .orElseThrow(() -> new EntityNotFoundException("Survey not found"));
        return entity.getLatestVersion();
    }

    @Override
    public Survey getSurvey(Survey.SurveyId surveyId, Long version) {
        SurveyJpaEntity surveyJpaEntity = surveyJpaRepository.findById(surveyId.value())
            .orElseThrow(() -> new EntityNotFoundException("Survey not found"));
        SurveyFormJpaEntity surveyFormJpaEntity = surveyFormJpaRepository.findBySurveyIdAndVersion(surveyId.value(), version)
            .orElseThrow(() -> new EntityNotFoundException("SurveyForm not found"));

        return Survey.update(
            new Survey.SurveyId(surveyJpaEntity.getId()),
            surveyFormJpaEntity.toModel()
        );
    }
}
