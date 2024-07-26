package org.ksh.survey.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ksh.common.exception.DataNotFoundException;
import org.ksh.common.model.BaseResponse;
import org.ksh.common.model.BaseStatusCode;
import org.ksh.survey.entity.SurveyTemplateItem;
import org.ksh.survey.entity.SurveyTemplate;
import org.ksh.survey.model.SurveySaveRequest;
import org.ksh.survey.model.SurveyUpdateRequest;
import org.ksh.survey.repository.SurveyTemplateItemRepository;
import org.ksh.survey.repository.SurveyTemplateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyTemplateService {

	private final SurveyTemplateRepository surveyTemplateRepository;
	private final SurveyTemplateItemRepository surveyTemplateItemRepository;
	private final EntityManager entityManager;

	@Transactional
	public BaseResponse saveSurveyTemplate(SurveySaveRequest surveySaveRequest) {
		SurveyTemplate surveyTemplate = surveyTemplateRepository.save(surveySaveRequest.createSurveyTemplate());
		List<SurveyTemplateItem> surveyTemplateItems = surveySaveRequest.createSurveyTemplateItems(surveyTemplate);
		surveyTemplateItemRepository.saveAll(surveyTemplateItems);
		return new BaseResponse(BaseStatusCode.SUCCESS.getMessage());
	}

	@Transactional
	public BaseResponse updateSurveyTemplate(SurveyUpdateRequest surveySaveRequest) {
		SurveyTemplate surveyTemplate = surveyTemplateRepository.findById(surveySaveRequest.getSurveyId())
				.orElseThrow(() -> new DataNotFoundException("아이디에 맞는 설문조사가 없습니다."));
		surveyTemplate.update(surveySaveRequest);

		List<SurveyTemplateItem> surveyTemplateItems = surveySaveRequest.createSurveyTemplateItems(surveyTemplate);
		surveyTemplateItemRepository.saveAll(surveyTemplateItems);

		return new BaseResponse(BaseStatusCode.SUCCESS.getMessage());
	}

}
