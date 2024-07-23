package com.inner_circle.survey.controller;

import com.inner_circle.survey.dto.request.SurveyRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveys")
public class SurveyController {

  @GetMapping
  public ResponseEntity<String> getSurveys() {
    return ResponseEntity.ok("get survey api called");
  }

  @PostMapping
  public ResponseEntity<String> createSurvey(@RequestBody SurveyRequest surveyRequest) {
    System.out.println(surveyRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body("create survey api called");
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
