package com.example.survey.usecase;

import java.util.Optional;
import java.util.stream.Collectors;

import com.example.survey.adapter.dto.SurveyDto;
import com.example.survey.adapter.persistence.SurveyPersistence;
import com.example.survey.adapter.persistence.SurveyVersionPersistence;
import com.example.survey.domain.mapper.SurveyMapper;
import com.example.survey.domain.model.Survey;
import com.example.survey.domain.model.SurveyItem;
import com.example.survey.domain.model.SurveyVersion;
import com.example.survey.exception.SurveyNotFoundException;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import static com.example.survey.domain.mapper.SurveyMapper.surveyToSurveyDto;

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

        return surveyToSurveyDto(savedSurvey);
    }

    public SurveyDto updateSurvey(Long id, SurveyDto surveyDto) {
        Optional<Survey> existingSurvey = surveyPersistence.findById(id);
        if (existingSurvey.isPresent()) {
            Survey survey = existingSurvey.get();
            int newVersion = survey.getCurrentVersion() + 1;

            // 새로운 버전 저장
            SurveyVersion surveyVersion = new SurveyVersion(survey, newVersion);
            surveyVersionPersistence.save(surveyVersion);

            // 설문조사 업데이트
            survey.setName(surveyDto.getName());
            survey.setDescription(surveyDto.getDescription());
            survey.setSurveyItems(surveyDto.getItems().stream()
                .map(SurveyMapper::surveyItemDtoToSurveyItem)
                .collect(Collectors.toList()));
            survey.setCurrentVersion(newVersion);
            Survey updatedSurvey = surveyPersistence.save(survey);

            return surveyToSurveyDto(updatedSurvey);
        } else {
            throw new SurveyNotFoundException("Survey not found with id: " + id);
        }
    }
}
