package com.innercircle.project_one.survey.domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@DiscriminatorValue("ELEMENT")
public class ElementSurveyObjectAnswer extends SurveyObjectAnswer {

    @ManyToOne
    @JoinColumn(name = "element_object_id")
    private ElementObject elementObject;
    private String answer;

}


