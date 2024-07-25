package com.example.survey.adapter.persistence;

import com.example.survey.domain.model.Survey;
import com.example.survey.domain.repository.SurveyRepository;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SurveyPersistence {

    private final SurveyRepository surveyRepository;

    public Survey save(Survey survey) {
        return surveyRepository.save(survey);
    }
}
