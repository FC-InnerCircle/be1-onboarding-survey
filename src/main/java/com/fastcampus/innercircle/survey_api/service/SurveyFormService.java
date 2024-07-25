package com.fastcampus.innercircle.survey_api.service;

import com.fastcampus.innercircle.survey_api.domain.entity.Question;
import com.fastcampus.innercircle.survey_api.domain.entity.SurveyForm;
import com.fastcampus.innercircle.survey_api.domain.request.SurveyFormRequest;
import com.fastcampus.innercircle.survey_api.repository.SurveyFormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SurveyFormService {

    private final SurveyFormRepository surveyFormRepository;

    public SurveyFormService(SurveyFormRepository surveyFormRepository) {
        this.surveyFormRepository = surveyFormRepository;
    }

    @Transactional
    public void saveSurveyForm(SurveyFormRequest surveyFormRequest) {
        SurveyForm surveyForm = new SurveyForm(surveyFormRequest.getTitle(), surveyFormRequest.getDescription());

        List<Question> questions = surveyFormRequest.getQuestions().stream()
                .map(Question::of)
                .collect(Collectors.toList());
        surveyForm.addQuestions(questions);

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
