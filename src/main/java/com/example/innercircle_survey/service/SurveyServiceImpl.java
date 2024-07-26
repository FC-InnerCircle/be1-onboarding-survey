package com.example.innercircle_survey.service;

import com.example.innercircle_survey.dto.SurveyVersionDTO;
import com.example.innercircle_survey.dto.ResponseDTO;
import com.example.innercircle_survey.entity.Response;
import com.example.innercircle_survey.entity.Survey;
import com.example.innercircle_survey.entity.SurveyVersion;
import com.example.innercircle_survey.repository.ResponseRepository;
import com.example.innercircle_survey.repository.SurveyRepository;
import com.example.innercircle_survey.repository.SurveyVersionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyVersionRepository surveyVersionRepository;
    private final ResponseRepository responseRepository;

    @Override
    @Transactional
    public void createSurvey(SurveyVersionDTO request) {
        // Survey 생성
        Survey survey = Survey.create(SurveyVersion.create(request));
        surveyRepository.save(survey);
    }

    @Override
    @Transactional
    public void updateSurvey(Long surveyId, SurveyVersionDTO request) {

        // 일단 무조건 not null 이라고 가정.. 예외처리 나중에
        Survey survey = surveyRepository.findById(surveyId).get();

        // SurveyVersion 자체는 새로 생성하는 게 맞는데..
        // SurveyVersion 과 관계를 맺은 다른 엔티티들은 변경 여부에 따라 생성하든지 그걸 그대로 넣든지 해야하는데..



    }


    @Override
    @Transactional
    public void createResponse(Long surveyId, ResponseDTO request) {
        Survey survey = surveyRepository.findById(surveyId).get();
        SurveyVersion surveyVersion = surveyVersionRepository.findById(survey.getRecentVersion().getId()).get();

        Response response = Response.create(request);
        response.setSurveyVersion(surveyVersion);
        responseRepository.save(response);
    }


    @Override
    @Transactional
    public List<ResponseDTO> readSurveyResponses(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId).get();
        List<ResponseDTO> result = new ArrayList<>();
        for(SurveyVersion surveyVersion : survey.getSurveyVersions()) {
            for(Response response : surveyVersion.getResponses()) {
                result.add(ResponseDTO.create(response));
            }
        }
        return result;
    }
}
