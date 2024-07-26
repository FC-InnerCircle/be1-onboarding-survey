package com.innercircle.onboardingservey.controller.survey;

import com.innercircle.onboardingservey.controller.survey.SurveyAdminResponse.SurveyDetailResponse;
import com.innercircle.onboardingservey.domain.SurveyService;
import com.innercircle.onboardingservey.domain.model.SurveyCommand.SurveyVersionCreateCommand;
import com.innercircle.onboardingservey.domain.model.SurveyResult.SurveyDetailResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/survey")
@RequiredArgsConstructor
public class SurveyAdminController {

    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyDetailResponse> surveyCreate(
        @RequestBody SurveyRequest.SurveyVersionCreateRequest request
    ) {
        final SurveyDetailResult surveyDetailResult = surveyService.createSurvey(SurveyVersionCreateCommand.from(request));
        return ResponseEntity.ok(SurveyDetailResponse.from(surveyDetailResult));
    }

    @PostMapping("/{surveyId}")
    public ResponseEntity<SurveyDetailResponse> surveyVersionCreate(
        @PathVariable Long surveyId,
        @RequestBody SurveyRequest.SurveyVersionCreateRequest request
    ) {
        final SurveyDetailResult surveyDetailResult = surveyService.addSurveyVersion(surveyId, SurveyVersionCreateCommand.from(request));
        return ResponseEntity.ok(SurveyDetailResponse.from(surveyDetailResult));
    }

}
