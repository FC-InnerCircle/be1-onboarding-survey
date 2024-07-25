package com.fastcampus.innercircle.survey_api.controller;

import com.fastcampus.innercircle.survey_api.domain.request.SurveyFormRequest;
import com.fastcampus.innercircle.survey_api.service.SurveyFormService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class SurveyController {

    private final SurveyFormService surveyFormService;

    public SurveyController(SurveyFormService surveyFormService) {
        this.surveyFormService = surveyFormService;
    }

    @PostMapping("/v1/forms")
    public void createSurveyForm(@RequestBody SurveyFormRequest surveyFormRequest) {

        surveyFormService.saveSurveyForm(surveyFormRequest);
    }

    @PutMapping("/v1/forms/{formId}")
    public void updateSurveyForm(
            @PathVariable(value = "formId") Long formId,
            @RequestBody SurveyFormRequest surveyFormRequest
    ) {
        surveyFormService.updateSurveyForm(formId, surveyFormRequest);
    }
}
