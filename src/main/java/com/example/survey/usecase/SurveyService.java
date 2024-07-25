package com.example.survey.usecase;

import com.example.survey.adapter.dto.SurveyDto;
import com.example.survey.adapter.persistence.SurveyPersistence;
import com.example.survey.adapter.persistence.SurveyVersionPersistence;
import com.example.survey.domain.mapper.SurveyMapper;
import com.example.survey.domain.model.Survey;
import com.example.survey.domain.model.SurveyVersion;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final SurveyPersistence surveyPersistence;
    private final SurveyVersionPersistence surveyVersionPersistence;

    public SurveyDto createSurvey(SurveyDto surveyDto) {
        Survey survey = SurveyMapper.surveyDtoToSurvey(surveyDto);

        Survey savedSurvey = surveyPersistence.save(survey);

        SurveyVersion surveyVersion = new SurveyVersion(savedSurvey, 1);
        surveyVersionPersistence.save(surveyVersion);

        savedSurvey.setCurrentVersion(1);
        surveyPersistence.save(savedSurvey);

        return SurveyMapper.surveyToSurveyDto(savedSurvey);
    }
}
