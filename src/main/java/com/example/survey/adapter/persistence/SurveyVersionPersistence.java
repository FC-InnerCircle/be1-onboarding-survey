package com.example.survey.adapter.persistence;

import com.example.survey.domain.model.SurveyVersion;
import com.example.survey.domain.repository.SurveyVersionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SurveyVersionPersistence {

    private final SurveyVersionRepository surveyVersionRepository;

    public SurveyVersion save(SurveyVersion surveyVersion) {
        return surveyVersionRepository.save(surveyVersion);
    }

    public List<SurveyVersion> findBySurveyId(Long surveyId) {
        return surveyVersionRepository.findBySurveyId(surveyId);
    }

    public SurveyVersion findBySurveyIdAndVersion(Long surveyId, int version) {
        return surveyVersionRepository.findBySurveyIdAndVersion(surveyId, version);
    }
}
