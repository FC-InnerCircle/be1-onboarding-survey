package com.innercircle.survey.domain.response;

import com.innercircle.survey.domain.survey.Survey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SurveyResponse {

    private Long surveyResponseId;

    private Survey survey;

    private String responseShortValue;

    private String responseLongValue;

    private List<SurveyResponse> surveyResponses;

}
