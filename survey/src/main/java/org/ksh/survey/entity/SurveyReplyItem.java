package org.ksh.survey.entity;

import jakarta.persistence.*;

@Entity
public class SurveyReplyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private SurveyItem surveyItem;

    private String response;
}
