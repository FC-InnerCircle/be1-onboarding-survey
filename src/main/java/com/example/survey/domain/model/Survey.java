package com.example.survey.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurveyItem> items;

    private int currentVersion;
}
