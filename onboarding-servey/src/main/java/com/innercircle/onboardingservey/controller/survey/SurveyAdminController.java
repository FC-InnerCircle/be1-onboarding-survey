package com.innercircle.onboardingservey.controller.survey;

import com.innercircle.onboardingservey.controller.survey.SurveyAdminResponse.SurveyDetailAdminResponse;
import com.innercircle.onboardingservey.domain.SurveyService;
import com.innercircle.onboardingservey.domain.model.SurveyCommand.SurveyVersionCreateCommand;
import com.innercircle.onboardingservey.domain.model.SurveyResults.SurveyAnswerResult;
import com.innercircle.onboardingservey.domain.model.SurveyResults.SurveyResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/surveys")
@RequiredArgsConstructor
public class SurveyAdminController {

    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyDetailAdminResponse> surveyCreate(
        @RequestBody SurveyAdminRequest.SurveyCreateAdminRequest request
    ) {
        final SurveyResult surveyResult = surveyService.createSurvey(SurveyVersionCreateCommand.from(request));
        return ResponseEntity.ok(SurveyDetailAdminResponse.from(surveyResult));
    }

    @PostMapping("/{surveyId}")
    public ResponseEntity<SurveyDetailAdminResponse> surveyVersionCreate(
        @PathVariable Long surveyId,
        @RequestBody SurveyAdminRequest.SurveyCreateAdminRequest request
    ) {
        final SurveyResult surveyResult = surveyService.addSurveyVersion(surveyId, SurveyVersionCreateCommand.from(request));
        return ResponseEntity.ok(SurveyDetailAdminResponse.from(surveyResult));
    }

    @GetMapping("{surveyId}/answer")
    public ResponseEntity<Object> getAnswer(
        @PathVariable Long surveyId
    ) {
        final List<SurveyAnswerResult> surveyResult = surveyService.getAnswer(surveyId);
        return ResponseEntity.ok(surveyResult);
    }

}
