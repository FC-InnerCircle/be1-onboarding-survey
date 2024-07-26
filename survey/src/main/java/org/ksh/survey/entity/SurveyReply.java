package org.ksh.survey.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyReply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;
    private Long version;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyTemplate surveyTemplate;

    @Builder
    public SurveyReply(Long id, String subjectName, Long version, SurveyTemplate surveyTemplate) {
        this.id = id;
        this.subjectName = subjectName;
        this.version = version;
        this.surveyTemplate = surveyTemplate;
    }

}
