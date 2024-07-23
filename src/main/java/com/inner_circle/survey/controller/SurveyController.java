package com.inner_circle.survey.controller;

import com.inner_circle.survey.dto.request.QuestionRequest;
import com.inner_circle.survey.dto.request.SurveyRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurveyController {

  @GetMapping("/survey")
  public ResponseEntity<String> test() {
    return ResponseEntity.ok("Hello, Survey");
  }

  @PostMapping("/survey")
  public ResponseEntity<String> test2(@RequestBody SurveyRequest surveyRequest) {
    System.out.println(surveyRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body("ok");
  }
}
