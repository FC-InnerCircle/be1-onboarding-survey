package com.psh10066.mock.survey_management.adapter.out.persistence;

import com.psh10066.survey.survey_management.application.port.out.FindSurveyPort;
import com.psh10066.survey.survey_management.application.port.out.ModifySurveyPort;
import com.psh10066.survey.survey_management.application.port.out.RegisterSurveyPort;
import com.psh10066.survey.survey_management.domain.Survey;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FakeSurveyPersistenceAdapter implements RegisterSurveyPort, ModifySurveyPort, FindSurveyPort {

    public List<Survey> data = new ArrayList<>();

    @Override
    public Survey.SurveyId register(Survey survey) {
        data.add(survey);
        return survey.getId();
    }

    @Override
    public Long getLatestVersion(Survey.SurveyId surveyId) {
        return data.stream()
            .filter(survey -> survey.getId().equals(surveyId))
            .findAny().orElseThrow(EntityNotFoundException::new)
            .getForm()
            .getVersion();
    }

    @Override
    public Survey.SurveyId modify(Survey survey) {
        data.removeIf(item -> Objects.equals(item.getId(), survey.getId()));
        data.add(survey);
        return survey.getId();
    }
}