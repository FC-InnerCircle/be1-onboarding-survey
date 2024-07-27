package com.inner_circle.survey.controller;

import com.inner_circle.survey.dto.request.RespondentRequest;
import com.inner_circle.survey.dto.request.SurveyRequest;
import com.inner_circle.survey.dto.response.RespondentResponse;
import com.inner_circle.survey.dto.response.RespondentResponseList;
import com.inner_circle.survey.dto.response.SurveyResponse;
import com.inner_circle.survey.dto.response.SurveyResponseList;
import com.inner_circle.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/surveys")
public class SurveyController {

  private final SurveyService surveyService;

  @GetMapping
  public ResponseEntity<SurveyResponseList> getSurveys() {
    List<SurveyResponse> surveyResponses = surveyService.getAllSurvey();
    return ResponseEntity.ok(new SurveyResponseList(surveyResponses));
  }

  @GetMapping("/{surveyId}")
  public ResponseEntity<SurveyResponse> getSurvey(@PathVariable("surveyId") Long surveyId) {
    SurveyResponse surveyResponse = surveyService.getSurvey(surveyId);
    return ResponseEntity.ok(surveyResponse);
  }

  @GetMapping("/{surveyId}/respondents")
  public ResponseEntity<RespondentResponseList> getRespondents(@PathVariable("surveyId") Long surveyId) {
    List<RespondentResponse> answers = surveyService.getAnswers(surveyId);
    return ResponseEntity.ok(new RespondentResponseList(answers));
  }

  @PostMapping
  public ResponseEntity<SurveyResponse> createSurvey(@RequestBody SurveyRequest surveyRequest) {
    SurveyResponse surveyResponse = surveyService.saveSurvey(surveyRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(surveyResponse);
  }

  @PostMapping("/{surveyId}")
  public ResponseEntity<Long> answerToSurvey(
      @PathVariable("surveyId") Long surveyId,
      @RequestBody RespondentRequest respondentRequest
  ) {
    Long respondentId = surveyService.answerToSurvey(surveyId, respondentRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(respondentId);
  }

  @PutMapping("/{surveyId}")
  public ResponseEntity<SurveyResponse> updateSurvey(
      @PathVariable("surveyId") Long surveyId,
      @RequestBody SurveyRequest surveyRequest
  ) {
    SurveyResponse surveyResponse = surveyService.updateSurvey(surveyId, surveyRequest);
    return ResponseEntity.ok(surveyResponse);
  }
}
