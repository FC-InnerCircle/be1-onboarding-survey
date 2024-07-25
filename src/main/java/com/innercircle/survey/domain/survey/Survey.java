package com.innercircle.survey.domain.survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Survey {

    private Long surveyId;

    private String surveyName;

    private String surveyDescription;

    private List<SurveyItem> surveyItems;
}
