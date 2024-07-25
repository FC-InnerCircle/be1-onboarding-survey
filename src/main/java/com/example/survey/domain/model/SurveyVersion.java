package com.example.survey.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long surveyId;
    private int version;

    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyItem> items;

    public SurveyVersion(Survey survey, int version) {
        this.surveyId = survey.getId();
        this.version = version;
        this.name = survey.getName();
        this.description = survey.getDescription();
        this.items = survey.getItems().stream()
                .map(SurveyItem::new)
                .collect(Collectors.toList());
    }
}
