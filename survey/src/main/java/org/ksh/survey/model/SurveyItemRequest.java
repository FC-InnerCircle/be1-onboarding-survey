package org.ksh.survey.model;

import lombok.Getter;
import org.ksh.survey.entity.SurveyTemplate;
import org.ksh.survey.entity.SurveyTemplateItem;

@Getter
public class SurveyItemRequest {
	private String itemName;
	private String itemDescription;
	private SurveyInputType surveyInputType;
	private boolean isRequired;

	public SurveyTemplateItem createSurveyTemplateItem(SurveyTemplate surveyTemplate) {
		return SurveyTemplateItem.builder()
				.description(itemDescription)
				.surveyInputType(surveyInputType)
				.name(itemName)
				.isRequired(isRequired)
				.surveyTemplate(surveyTemplate)
				.build();
	}

}
