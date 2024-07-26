package org.ksh.survey.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ksh.survey.model.SurveyInputType;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SurveyReplyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyTemplateItem surveyTemplateItem;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyReply surveyReply;

    private String itemReply;
    private String itemName;
    private SurveyInputType surveyInputType;
    private boolean isRequired;

    @Builder

    public SurveyReplyItem(Long id, SurveyTemplateItem surveyTemplateItem, SurveyReply surveyReply, String itemReply, String itemName, SurveyInputType surveyInputType, boolean isRequired) {
        this.id = id;
        this.surveyTemplateItem = surveyTemplateItem;
        this.surveyReply = surveyReply;
        this.itemReply = itemReply;
        this.itemName = itemName;
        this.surveyInputType = surveyInputType;
        this.isRequired = isRequired;
    }

}
