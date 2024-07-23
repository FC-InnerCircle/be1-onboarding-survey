package com.inner_circle.survey.controller;

import com.inner_circle.survey.dto.request.SurveyRequest;
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
  public ResponseEntity<SurveyResponse> updateSurvey(@PathVariable("surveyId") Long surveyId) {
    SurveyResponse surveyResponse = surveyService.getSurvey(surveyId);
    return ResponseEntity.ok(surveyResponse);
  }

  @PostMapping
  public ResponseEntity<SurveyResponse> createSurvey(@RequestBody SurveyRequest surveyRequest) {
    SurveyResponse surveyResponse = surveyService.saveSurvey(surveyRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(surveyResponse);
  }

  @PutMapping("/{surveyId}")
  public ResponseEntity<String> updateSurvey(
      @PathVariable("surveyId") Long surveyId,
      @RequestBody SurveyRequest surveyRequest
  ) {
    System.out.println(surveyId);
    return ResponseEntity.ok("update survey api called");
  }
}
