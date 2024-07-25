package com.example.survey.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @SuppressWarnings("CopyConstructorMissesField")
    public SurveyItem(SurveyItem item) {
        this.name = item.getName();
        this.description = item.getDescription();
        this.inputType = item.getInputType();
        this.required = item.isRequired();
        this.options = Optional.ofNullable(item.getOptions())
                .map(ArrayList::new)
                .orElseGet(ArrayList::new);
    }
}
