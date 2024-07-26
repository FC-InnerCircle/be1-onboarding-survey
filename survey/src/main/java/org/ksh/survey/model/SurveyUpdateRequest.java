package org.ksh.survey.model;

import lombok.Getter;

import java.util.List;

@Getter
public class SurveyUpdateRequest extends SurveySaveRequest {
	private long surveyId;
	private List<Long> surveyItemId;

}
