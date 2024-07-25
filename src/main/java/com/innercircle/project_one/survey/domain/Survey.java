package com.innercircle.project_one.survey.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @OneToOne(mappedBy = "survey", cascade = CascadeType.ALL)
    private SurveyVersion surveyVersion;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<SurveyObject> surveyObjects = new ArrayList<>();

}
