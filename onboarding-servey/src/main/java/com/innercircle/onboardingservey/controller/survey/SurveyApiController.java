package com.innercircle.onboardingservey.controller.survey;

import com.innercircle.onboardingservey.domain.SurveyService;

import com.innercircle.onboardingservey.domain.model.AnswerCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
public class SurveyApiController {
    private final SurveyService surveyService;

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyApiResponse.SurveyDetailApiResponse> getSurvey(
        @PathVariable Long surveyId
    ) {
        return ResponseEntity.ok(SurveyApiResponse.SurveyDetailApiResponse.from(surveyService.getSurveyLatestVersion(surveyId)));
    }

    @PostMapping("/answers")
    public ResponseEntity<Object> createAnswer(
        @RequestBody SurveyApiRequest.AnswerApiCreateRequest request
    ) {
        return ResponseEntity.ok(surveyService.createAnswer(AnswerCommand.AnswerCreateCommand.from(request)));
    }
}
