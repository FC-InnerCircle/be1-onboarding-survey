package com.example.survey.usecase;

import com.example.survey.adapter.persistence.SurveyPersistence;
import com.example.survey.domain.model.Survey;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyPersistence surveyPersistence;

    public Survey createSurvey(Survey survey) {
        return surveyPersistence.save(survey);
    }
}
