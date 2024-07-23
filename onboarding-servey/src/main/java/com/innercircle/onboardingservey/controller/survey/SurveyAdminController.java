package com.innercircle.onboardingservey.controller.survey;

import com.innercircle.onboardingservey.controller.survey.SurveyAdminResponse.SurveyDetailResponse;
import com.innercircle.onboardingservey.domain.SurveyService;
import com.innercircle.onboardingservey.domain.model.SurveyCommand.SurveyCreateCommand;
import com.innercircle.onboardingservey.domain.model.SurveyResult.SurveyDetailResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/survey")
@RequiredArgsConstructor
public class SurveyAdminController {

    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyDetailResponse> create(
        @RequestBody SurveyRequest.SurveyCreateRequest request
    ) {
        final SurveyDetailResult surveyDetail = surveyService.create(SurveyCreateCommand.from(request));
        return ResponseEntity.ok(SurveyDetailResponse.from(surveyDetail));
    }

}
