package org.ksh.survey.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SurveyReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyTemplate surveyTemplate;

}
