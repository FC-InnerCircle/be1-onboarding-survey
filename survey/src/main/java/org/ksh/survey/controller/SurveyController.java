package org.ksh.survey.controller;

import lombok.RequiredArgsConstructor;
import org.ksh.common.model.BaseResponse;
import org.ksh.survey.model.SurveySaveRequest;
import org.ksh.survey.model.SurveyUpdateRequest;
import org.ksh.survey.service.SurveyTemplateService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/survey")
@RequiredArgsConstructor
public class SurveyController {

	private final SurveyTemplateService surveyTemplateService;

	@PostMapping
	public BaseResponse storeSurveyTemplate(@RequestBody SurveySaveRequest surveySaveRequest) {
		return surveyTemplateService.saveSurveyTemplate(surveySaveRequest);
	}

	@PutMapping
	public BaseResponse editSurveyTemplate(@RequestBody SurveyUpdateRequest surveySaveRequest) {
		return surveyTemplateService.updateSurveyTemplate(surveySaveRequest);
	}

}
