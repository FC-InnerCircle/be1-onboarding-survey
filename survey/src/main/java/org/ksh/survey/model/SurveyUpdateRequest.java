package org.ksh.survey.model;

import lombok.Getter;
import org.ksh.survey.entity.SurveyTemplate;
import org.ksh.survey.entity.SurveyTemplateItem;

import java.util.List;

@Getter
public class SurveyUpdateRequest {
	private String surveyName;
	private String surveyDescription;
	List<SurveyItemRequest> surveyItemRequests;

	public SurveyTemplate createSurveyTemplate() {
		return SurveyTemplate.builder()
				.name(surveyName)
				.description(surveyDescription)
				.build();
	}

	public List<SurveyTemplateItem> createSurveyTemplateItems(SurveyTemplate surveyTemplate) {
		return surveyItemRequests.stream()
				.map(surveyItemRequest -> surveyItemRequest.createSurveyTemplateItem(surveyTemplate))
				.toList();
	}

}
