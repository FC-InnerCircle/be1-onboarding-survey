package com.innercircle.survey.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class SurveyResponseDetail {

    private Long responseDetailId;

    private SurveyResponse response;

    private String responseValue;
}
