package org.ksh.survey.model;

import lombok.Getter;
import org.ksh.survey.entity.SurveyReply;
import org.ksh.survey.entity.SurveyReplyItem;
import org.ksh.survey.entity.SurveyTemplateItem;

@Getter
public class SurveyReplyItemRequest {
    private long itemId;
    private String reply;
    private String name;
    private SurveyInputType surveyInputType;
    private boolean isRequired;

    public SurveyReplyItem createSurveyReplyItem(SurveyReply surveyReply, SurveyTemplateItem surveyTemplateItem) {
        return SurveyReplyItem.builder()
                .itemName(name)
                .surveyInputType(surveyInputType)
                .isRequired(isRequired)
                .surveyReply(surveyReply)
                .itemReply(reply)
                .surveyTemplateItem(surveyTemplateItem)
                .build();
    }

}
