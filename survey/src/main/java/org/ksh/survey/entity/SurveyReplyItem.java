package org.ksh.survey.entity;

import jakarta.persistence.*;

@Entity
public class SurveyReplyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyTemplateItem surveyTemplateItem;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyReply surveyReply;

    private String response;
}
