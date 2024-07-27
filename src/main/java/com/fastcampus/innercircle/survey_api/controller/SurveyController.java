package com.fastcampus.innercircle.survey_api.controller;

import com.fastcampus.innercircle.survey_api.common.Response;
import com.fastcampus.innercircle.survey_api.domain.request.ResponseRequest;
import com.fastcampus.innercircle.survey_api.domain.request.SurveyFormRequest;
import com.fastcampus.innercircle.survey_api.domain.response.SurveyResponse;
import com.fastcampus.innercircle.survey_api.service.ResponseService;
import com.fastcampus.innercircle.survey_api.service.SurveyFormService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class SurveyController {

    private final SurveyFormService surveyFormService;
    private final ResponseService responseService;

    public SurveyController(SurveyFormService surveyFormService, ResponseService responseService) {
        this.surveyFormService = surveyFormService;
        this.responseService = responseService;
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

    @PostMapping("/v1/forms/{formId}/responses")
    public void submitResponse(
            @PathVariable(value = "formId") Long formId,
            @RequestBody ResponseRequest responseRequest
            ) {
        responseService.submitResponse(formId, responseRequest);
    }

    @GetMapping("/v1/forms/{formId}/responses")
    public Response<List<SurveyResponse>> getResponses(@PathVariable(value = "formId") Long formId) {
        return Response.of(responseService.getResponses(formId));
    }
}
