package com.innercircle.project_one.survey.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Entity
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("ELEMENT")
public class ElementSurveyObjectAnswer extends SurveyObjectAnswer {

    @ManyToOne
    @JoinColumn(name = "element_object_id")
    private ElementObject elementObject;
    private String answer;

}


