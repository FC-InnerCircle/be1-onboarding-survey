package com.psh10066.mock.survey_management.adapter.out.persistence;

import com.psh10066.survey.survey_management.application.port.out.RegisterSurveyPort;
import com.psh10066.survey.survey_management.domain.Survey;

import java.util.ArrayList;
import java.util.List;

public class FakeSurveyRepository implements RegisterSurveyPort {

    public List<Survey> data = new ArrayList<>();

    @Override
    public Survey.SurveyId register(Survey survey) {
        data.add(survey);
        return survey.getId();
    }
}