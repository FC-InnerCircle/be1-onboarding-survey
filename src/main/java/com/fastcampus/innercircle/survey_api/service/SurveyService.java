package com.fastcampus.innercircle.survey_api.service;

import com.fastcampus.innercircle.survey_api.domain.entity.SurveyForm;
import com.fastcampus.innercircle.survey_api.domain.request.SurveyFormRequest;
import com.fastcampus.innercircle.survey_api.repository.SurveyFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SurveyService {

    private final SurveyFormRepository surveyFormRepository;

    public SurveyService(SurveyFormRepository surveyFormRepository) {
        this.surveyFormRepository = surveyFormRepository;
    }

    @Transactional
    public void saveSurveyForm(SurveyFormRequest surveyFormRequest) {
        SurveyForm surveyForm = new SurveyForm(surveyFormRequest.getTitle(), surveyFormRequest.getDescription());
        surveyFormRepository.save(surveyForm);
    }

    @Transactional
    public void updateSurveyForm(long formId, SurveyFormRequest surveyFormRequest) {
        SurveyForm surveyForm = surveyFormRepository.findById(formId).orElseThrow();
        surveyForm.setTitle(surveyFormRequest.getTitle());
        surveyForm.setDescription(surveyFormRequest.getDescription());
        surveyForm.setUpdatedAt(LocalDateTime.now());
    }
}
