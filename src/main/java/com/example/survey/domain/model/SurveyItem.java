package com.example.survey.domain.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class SurveyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private InputType inputType;

    private boolean required;

    @ElementCollection
    private List<String> options;
}
