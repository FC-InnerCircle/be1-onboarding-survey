package com.innercircle.project_one.survey.domain;

import com.innercircle.project_one.survey.common.SurveyObjectDataType;
import com.innercircle.project_one.survey.domain.embeddable.SurveyObjectContent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
public class SurveyObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private SurveyObjectDataType type;

    @Embedded
    private SurveyObjectContent surveyObjectContent;

    private int elementOrder;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    private Long surveyVersionId;

    public void updateSurveyVersion(Long version) {
        this.surveyVersionId = version;
    }

}