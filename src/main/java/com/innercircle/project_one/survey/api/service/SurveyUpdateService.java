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
public class SurveyUpdateService {

    private final SurveyService surveyService;
    private final SurveyRepository surveyRepository;
    private final SurveyVersionRepository surveyVersionRepository;

    @Transactional
    public ApiResponse updateSurvey(Long surveyId, SurveyDTO surveyDTO) {
        Survey findSurvey = surveyService.findSurvey(surveyId);
        findSurvey.updateSurveyTitleAndDescription(surveyDTO.title(), surveyDTO.description());
        Survey savedSurvey = surveyRepository.save(findSurvey);

        SurveyVersion latestVersion = surveyVersionRepository.findTopBySurveyOrderByVersionDesc(savedSurvey);
        latestVersion.setVersion(latestVersion.getVersion() + 1);

        surveyService.saveSurveyObjects(savedSurvey, surveyDTO, latestVersion);
        return new SuccessResponse<>("설문조사 폼이 업데이트되었습니다.");
    }


}
