package com.innercircle.survey.domain.survey;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SurveyItem {


    private Long surveyItemId;

    private String surveyItemName;

    private String surveyItemDescription;

    private boolean required;

    private Survey survey;

}
