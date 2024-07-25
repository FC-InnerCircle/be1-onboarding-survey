package com.innercircle.project_one.survey.api;

import com.innercircle.project_one.survey.api.dto.SurveyDTO;
import com.innercircle.project_one.survey.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createSurvey(@RequestBody SurveyDTO surveyDTO) {
        ApiResponse response = surveyService.saveSurvey(surveyDTO);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

}
