package com.example.innercircle_survey.controller;

import com.example.innercircle_survey.dto.SurveyRequestDTO;
import com.example.innercircle_survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;

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
@Controller
public class SurveyController {

    private final SurveyService service;

    // 설문조사 등록
    @PostMapping("/survey")
    public HttpResponse<Object> createSurvey(@RequestBody @Valid SurveyRequestDTO request) {

        return null;
    }

    // 설문조사 수정
    @PutMapping("/survey/{surveyId}")
    public HttpResponse<Object> updateSurvey(
            @PathVariable("surveyId") String surveyId,
            @RequestBody @Valid SurveyRequestDTO request
    ) {

        return null;
    }

    // 설문조사 응답 제출
    @PostMapping("/survey/{surveyId}")
    public HttpResponse<Object> createResponse(@PathVariable("surveyId") String surveyId) {

        return null;
    }

    // 설문조사 응답 조회
    @GetMapping("/survey/{surveyId}")
    public HttpResponse<Object> readSurveyResponses(@PathVariable("surveyId") String surveyId) {

        return null;
    }
}
