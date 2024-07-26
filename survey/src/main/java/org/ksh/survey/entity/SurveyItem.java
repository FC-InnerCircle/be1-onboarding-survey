package org.ksh.survey.entity;

import jakarta.persistence.*;
import org.ksh.survey.model.InputType;

@Entity
public class SurveyItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private InputType inputType;

    private boolean isRequired;

}
