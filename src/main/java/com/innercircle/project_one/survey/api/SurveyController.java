package com.innercircle.project_one.survey.api;

import com.innercircle.project_one.survey.api.dto.SurveyDTO;
import com.innercircle.project_one.survey.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{surveyId}")
    public ResponseEntity<ApiResponse> modifySurvey(@RequestBody SurveyDTO surveyDTO,
                                                    @PathVariable Long surveyId) {
        ApiResponse response = surveyService.updateSurvey(surveyId, surveyDTO);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

}
