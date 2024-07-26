package com.example.innercircle_survey.service;

import com.example.innercircle_survey.dto.SurveyVersionDTO;
import com.example.innercircle_survey.dto.ResponseDTO;

import java.util.List;

public interface SurveyService {

    public void createSurvey(SurveyVersionDTO request);

    public void updateSurvey(Long surveyId, SurveyVersionDTO request);

    public void createResponse(Long surveyId, ResponseDTO request);

    public List<ResponseDTO> readSurveyResponses(Long surveyId);
}
