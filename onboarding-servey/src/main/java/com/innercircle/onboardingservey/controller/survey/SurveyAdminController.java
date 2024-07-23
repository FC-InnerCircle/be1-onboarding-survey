package com.innercircle.onboardingservey.controller.survey;

import com.innercircle.onboardingservey.controller.survey.SurveyAdminResponse.SurveyDetailResponse;
import com.innercircle.onboardingservey.domain.SurveyService;
import com.innercircle.onboardingservey.domain.model.SurveyCommand.SurveyCreateCommand;
import com.innercircle.onboardingservey.domain.model.SurveyCommand.SurveyUpdateCommand;
import com.innercircle.onboardingservey.domain.model.SurveyResult.SurveyDetailResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        final SurveyDetailResult surveyDetailResult = surveyService.create(SurveyCreateCommand.from(request));
        return ResponseEntity.ok(SurveyDetailResponse.from(surveyDetailResult));
    }

    @PutMapping
    public ResponseEntity<SurveyDetailResponse> update(
        @RequestBody SurveyRequest.SurveyUpdateRequest request
    ) {
        final SurveyDetailResult surveyDetailResult = surveyService.update(SurveyUpdateCommand.from(request));
        return ResponseEntity.ok(SurveyDetailResponse.from(surveyDetailResult));
    }

}
