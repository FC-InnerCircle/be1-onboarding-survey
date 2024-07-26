package org.ksh.survey.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ksh.survey.entity.SurveyReplyItem;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyReplyItemResponses {

    private List<SurveyReplyItemResponse> surveyReplyItemResponses;

    public SurveyReplyItemResponses(List<SurveyReplyItemResponse> surveyReplyItemResponses) {
        this.surveyReplyItemResponses = surveyReplyItemResponses;
    }

    public static SurveyReplyItemResponses createSurveyReplyItemResponses(List<SurveyReplyItem> surveyReplyItems) {
        return new SurveyReplyItemResponses(surveyReplyItems.stream()
                .map(surveyReplyItem -> SurveyReplyItemResponse.builder()
                        .surveyItemReply(surveyReplyItem.getItemReply())
                        .surveyItemName(surveyReplyItem.getItemName())
                        .isRequired(surveyReplyItem.isRequired())
                        .surveyInputType(surveyReplyItem.getSurveyInputType())
                        .build())
                .toList());

    }

}
