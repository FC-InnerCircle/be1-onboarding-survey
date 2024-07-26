package org.ksh.survey.model;

import lombok.Getter;
import org.ksh.survey.entity.SurveyTemplate;
import org.ksh.survey.entity.SurveyTemplateItem;

import java.util.UUID;

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
				// 조회 시 조인 비용 아끼려고 생성한 필드
				.version(surveyTemplate.getVersion() + 1)
				.build();
	}

}
