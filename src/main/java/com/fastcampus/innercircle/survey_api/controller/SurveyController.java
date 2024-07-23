package com.fastcampus.innercircle.survey_api.controller;

import com.fastcampus.innercircle.survey_api.domain.request.SurveyFormRequest;
import com.fastcampus.innercircle.survey_api.service.SurveyService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("/v1/forms")
    public void createSurveyForm(@RequestBody SurveyFormRequest surveyFormRequest) {

        surveyService.saveSurveyForm(surveyFormRequest);
    }

    @PutMapping("/v1/forms/{formId}")
    public void updateSurveyForm(
            @PathVariable(value = "formId") long formId,
            @RequestBody SurveyFormRequest surveyFormRequest
    ) {
        surveyService.updateSurveyForm(formId, surveyFormRequest);
    }
}
