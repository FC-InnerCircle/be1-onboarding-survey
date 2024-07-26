package org.ksh.survey.service;

import lombok.RequiredArgsConstructor;
import org.ksh.common.model.BaseResponse;
import org.ksh.common.model.BaseStatusCode;
import org.ksh.survey.entity.SurveyTemplateItem;
import org.ksh.survey.entity.SurveyTemplate;
import org.ksh.survey.model.SurveySaveRequest;
import org.ksh.survey.repository.SurveyTemplateItemRepository;
import org.ksh.survey.repository.SurveyTemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyTemplateService {

	private final SurveyTemplateRepository surveyTemplateRepository;
	private final SurveyTemplateItemRepository surveyTemplateItemRepository;

	@Transactional
	public BaseResponse saveSurveyTemplate(SurveySaveRequest surveySaveRequest) {
		SurveyTemplate surveyTemplate = surveyTemplateRepository.save(surveySaveRequest.createSurveyTemplate());
		List<SurveyTemplateItem> surveyTemplateItems = surveySaveRequest.createSurveyTemplateItems(surveyTemplate);
		surveyTemplateItemRepository.saveAll(surveyTemplateItems);
		return new BaseResponse(BaseStatusCode.SUCCESS.getMessage());
	}

}
