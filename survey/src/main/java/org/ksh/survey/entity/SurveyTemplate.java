package org.ksh.survey.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SurveyTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany
    private List<SurveyItem> items;
}
