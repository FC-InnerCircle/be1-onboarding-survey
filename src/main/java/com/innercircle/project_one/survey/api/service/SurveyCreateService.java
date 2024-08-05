package com.innercircle.project_one.survey.api.service;

import com.innercircle.project_one.survey.api.dto.SurveyDTO;
import com.innercircle.project_one.survey.api.repository.*;
import com.innercircle.project_one.survey.common.ApiResponse;
import com.innercircle.project_one.survey.common.SuccessResponse;
import com.innercircle.project_one.survey.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SurveyCreateService {

    private final SurveyRepository surveyRepository;
    private final SurveyVersionRepository surveyVersionRepository;
    private final SurveyService surveyService;


    @Transactional
    public ApiResponse saveSurvey(SurveyDTO surveyDTO) {
        Survey survey = createSurvey(surveyDTO);
        SurveyVersion newSurveyVersion = createSurveyVersion(survey);
        surveyService.saveSurveyObjects(survey, surveyDTO, newSurveyVersion);
        return new SuccessResponse<>("설문조사 폼이 저장되었습니다.");
    }


    private Survey createSurvey(SurveyDTO surveyDTO) {
        return surveyRepository.save(new Survey(surveyDTO.title(), surveyDTO.description()));
    }

    private SurveyVersion createSurveyVersion(Survey survey) {
        return surveyVersionRepository.save(new SurveyVersion(1L, survey));
    }


}
