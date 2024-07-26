package com.innercircle.survey.controller;

import com.innercircle.survey.common.ApiResponse;
import com.innercircle.survey.common.EmptyJsonResponse;
import com.innercircle.survey.dto.survey.SurveyReq;
import com.innercircle.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
