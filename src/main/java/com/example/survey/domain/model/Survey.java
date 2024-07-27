package com.example.survey.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "survey")
    @Builder.Default private List<SurveyItem> surveyItems = new ArrayList<>();
    @OneToMany(mappedBy = "survey")
    @Builder.Default private List<SurveyVersion> surveyVersion = new ArrayList<>();

    private int currentVersion;
}
