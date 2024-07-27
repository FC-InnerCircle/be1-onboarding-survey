package com.innercircle.project_one.survey.api;

import com.innercircle.project_one.survey.api.dto.SurveyDTO;
import com.innercircle.project_one.survey.api.dto.SurveySubmitDTO;
import com.innercircle.project_one.survey.api.service.SurveyAnswerService;
import com.innercircle.project_one.survey.api.service.SurveyCreateService;
import com.innercircle.project_one.survey.api.service.SurveyUpdateService;
import com.innercircle.project_one.survey.common.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveys")
@AllArgsConstructor
public class SurveyController {

    private final SurveyCreateService surveyCreateService;
    private final SurveyUpdateService surveyUpdateService;
    private final SurveyAnswerService surveyAnswerService;

    @PostMapping
    public ResponseEntity<ApiResponse> createSurvey(@RequestBody SurveyDTO surveyDTO) {
        ApiResponse response = surveyCreateService.saveSurvey(surveyDTO);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @PatchMapping("/{surveyId}")
    public ResponseEntity<ApiResponse> modifySurvey(@RequestBody SurveyDTO surveyDTO,
                                                    @PathVariable Long surveyId) {
        ApiResponse response = surveyUpdateService.updateSurvey(surveyId, surveyDTO);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @PostMapping("/{surveyId}")
    public ResponseEntity<ApiResponse> submitSurvey(@RequestBody SurveySubmitDTO surveySubmitDTO,
                                                    @PathVariable Long surveyId) {
        ApiResponse response = surveyAnswerService.submitSurveyResponse(surveyId, surveySubmitDTO);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

}
