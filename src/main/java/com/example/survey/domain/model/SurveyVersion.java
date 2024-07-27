package com.example.survey.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyVersion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_version_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;
    private int version;

    private String name;
    private String description;

    @OneToMany(mappedBy = "surveyVersion")
    @Builder.Default private List<SurveyItem> surveyItems = new ArrayList<>();

    public SurveyVersion(Survey survey, int version) {
        this.survey = survey;
        this.version = version;
        this.name = survey.getName();
        this.description = survey.getDescription();
        this.surveyItems = survey.getSurveyItems().stream()
                .map(SurveyItem::new)
                .collect(Collectors.toList());
    }
}
