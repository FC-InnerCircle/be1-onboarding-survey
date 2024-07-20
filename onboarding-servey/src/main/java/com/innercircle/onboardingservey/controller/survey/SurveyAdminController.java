package com.innercircle.onboardingservey.controller.survey;

import com.innercircle.onboardingservey.domain.SurveyService;
import com.innercircle.onboardingservey.domain.model.SurveyCommand.SurveyCreateCommand;
import com.innercircle.onboardingservey.domain.model.SurveyResult.SurveyDetail;
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
    public ResponseEntity<SurveyAdminResponse.SurveyDetail> create(
        @RequestBody SurveyRequest.SurveyCreateRequest request
    ) {
        final SurveyDetail surveyDetail = surveyService.create(SurveyCreateCommand.from(request));
        return ResponseEntity.ok(SurveyAdminResponse.SurveyDetail.from(surveyDetail));
    }

}
