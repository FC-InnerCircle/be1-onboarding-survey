package com.example.survey.adapter.controller;

import com.example.survey.adapter.dto.ApiResponse;
import com.example.survey.adapter.dto.SurveyDto;
import com.example.survey.usecase.SurveyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<ApiResponse<SurveyDto>> createSurvey(@Valid @RequestBody SurveyDto surveyDto) {
        SurveyDto createdSurvey = surveyService.createSurvey(surveyDto);
        ApiResponse<SurveyDto> response = ApiResponse.<SurveyDto>builder()
                .status("success")
                .message("Survey created successfully")
                .data(createdSurvey)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyDto>> updateSurvey(@PathVariable Long id, @Valid @RequestBody SurveyDto surveyDto) {
        SurveyDto updatedSurvey = surveyService.updateSurvey(id, surveyDto);
        ApiResponse<SurveyDto> response = ApiResponse.<SurveyDto>builder()
            .status("success")
            .message("Survey updated successfully")
            .data(updatedSurvey)
            .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
