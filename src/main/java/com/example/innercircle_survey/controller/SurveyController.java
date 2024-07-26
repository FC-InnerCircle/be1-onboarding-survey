package com.example.innercircle_survey.controller;

import com.example.innercircle_survey.dto.SurveyVersionDTO;
import com.example.innercircle_survey.dto.ResponseDTO;
import com.example.innercircle_survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * [요구사항]
 * 1. 설문조사 생성 API  ( POST : /survey )
 * 2. 설문조사 수정 API ( PUT : /survey/{survey_id} )
 * 3. 설문조사 응답 제출 API ( POST : /survey/{survey_id} )
 * 4. 설문조사 응답 조회 API ( GET : /survey/{survey_id} )
 *
 * */

@RequiredArgsConstructor
@RestController
public class SurveyController {

    private final SurveyService service;

    // 설문조사 등록
    @PostMapping("/surveys")
    public void createSurvey(@RequestBody @Valid SurveyVersionDTO request) {
        service.createSurvey(request);
    }

    // 설문조사 수정
    @PutMapping("/survey/{surveyId}")
    public void updateSurvey(
            @PathVariable("surveyId") Long surveyId,
            @RequestBody @Valid SurveyVersionDTO request
    ) {
        service.updateSurvey(surveyId, request);
    }

    // 설문조사 응답 제출
    @PostMapping("/surveys/{surveyId}/responses")
    public void createResponse(
            @PathVariable("surveyId") Long surveyId,
            @RequestBody @Valid ResponseDTO request) {

        service.createResponse(surveyId, request);
    }

    // 설문조사 응답 조회
    @GetMapping("/surveys/{surveyId}/responses")
    public List<ResponseDTO> readSurveyResponses(@PathVariable("surveyId") Long surveyId) {

        return service.readSurveyResponses(surveyId);
    }
}
