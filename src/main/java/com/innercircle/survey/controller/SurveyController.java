package com.innercircle.survey.controller;

import com.innercircle.survey.dto.request.SubmitResponseRequestDto;
import com.innercircle.survey.dto.request.SurveyCreateRequestDto;
import com.innercircle.survey.dto.request.SurveyUpdateRequestDto;
import com.innercircle.survey.dto.response.SurveyResponseDto;
import com.innercircle.survey.dto.response.SurveyResponseResponseDto;
import com.innercircle.survey.dto.response.SurveyResponseResponseDtos;
import com.innercircle.survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/v1/api/surveys")
@RestController
public class SurveyController {
    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyResponseDto> createSurvey(
            @RequestBody SurveyCreateRequestDto surveyCreateRequestDto
    ) {
        SurveyResponseDto responseDto = surveyService.createSurvey(surveyCreateRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{surveyId}")
    public ResponseEntity<SurveyResponseDto> updateSurvey(
            @PathVariable("surveyId") Long surveyId,
            @RequestBody SurveyUpdateRequestDto surveyUpdateRequestDto
    ) {
        SurveyResponseDto responseDto = surveyService.updateSurvey(surveyId, surveyUpdateRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}
