package com.innercircle.project_one.survey.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@Getter
@DiscriminatorValue("STRING")
public class ElementSurveyObject extends SurveyObject {

    @Builder.Default
    @OneToMany(mappedBy = "surveyObject", cascade = CascadeType.ALL)
    private List<ElementObject> elementObjects = new ArrayList<>();


}

