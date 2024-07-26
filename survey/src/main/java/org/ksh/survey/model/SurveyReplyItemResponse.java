package org.ksh.survey.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyReplyItemResponse {
    private String surveyItemName;
    private String surveyItemReply;
    private SurveyInputType surveyInputType;
    private boolean isRequired;

    @Builder
    public SurveyReplyItemResponse(String surveyItemName, String surveyItemReply, SurveyInputType surveyInputType, boolean isRequired) {
        this.surveyItemName = surveyItemName;
        this.surveyItemReply = surveyItemReply;
        this.surveyInputType = surveyInputType;
        this.isRequired = isRequired;
    }

}
