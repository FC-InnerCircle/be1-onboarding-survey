package com.innercircle.survey.controller;

import com.innercircle.survey.common.ApiResponse;
import com.innercircle.survey.common.EmptyJsonResponse;
import com.innercircle.survey.dto.survey.SurveyReq;
import com.innercircle.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping("/surveys")
    public ApiResponse<EmptyJsonResponse> createSurvey(
        @RequestBody SurveyReq surveyReq) {
        surveyService.createSurvey(surveyReq);
        return ApiResponse.success();
    }

    @PutMapping("/surveys/{id}")
    public ApiResponse<EmptyJsonResponse> updateSurvey(
        @PathVariable Long id, @RequestBody SurveyReq surveyReq) {
        surveyService.updateSurvey(id, surveyReq);
        return ApiResponse.success();
    }
}
