package org.ksh.survey.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ksh.survey.model.SurveyInputType;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyTemplateItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private Long version;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private SurveyInputType surveyInputType;

    private boolean isRequired;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyTemplate surveyTemplate;

    @Builder
    public SurveyTemplateItem(Long id, Long version, String name, String description, SurveyInputType surveyInputType, boolean isRequired, SurveyTemplate surveyTemplate) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.description = description;
        this.surveyInputType = surveyInputType;
        this.isRequired = isRequired;
        this.surveyTemplate = surveyTemplate;
    }

}
