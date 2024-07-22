package com.example.innercircle_survey.controller;

import com.example.innercircle_survey.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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

    @PostMapping("/survey")
    public HttpResponse<Object> insertSurvey() {

        return null;
    }

    @PutMapping("/survey/{surveyId}")
    public HttpResponse<Object> updateSurvey(@PathVariable("surveyId") String surveyId) {

        return null;
    }

    @PostMapping("/survey/{surveyId}")
    public HttpResponse<Object> insertResponse(@PathVariable("surveyId") String surveyId) {

        return null;
    }

    @GetMapping("/survey/{surveyId}")
    public HttpResponse<Object> selectSurveyResponse(@PathVariable("surveyId") String surveyId) {

        return null;
    }
}
