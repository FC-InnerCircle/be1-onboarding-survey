package com.example.survey.adapter.persistence;

import java.util.Optional;

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

    public Optional<Survey> findById(Long id) {
        return surveyRepository.findById(id);
    }
}
