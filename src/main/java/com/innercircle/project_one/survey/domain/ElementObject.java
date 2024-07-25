package com.innercircle.project_one.survey.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

@Entity
@Getter
public class ElementObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int elementOrder;
    private String elementValue;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "survey_object_id")
    private SurveyObject surveyObject;

    public ElementObject(int elementOrder, String elementValue, SurveyObject elementSurveyObject) {
       this.elementOrder = Objects.requireNonNull(elementOrder);
       this.elementValue = Objects.requireNonNull(elementValue);
       this.surveyObject = Objects.requireNonNull(elementSurveyObject);
    }

}
