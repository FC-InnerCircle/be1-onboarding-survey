package org.ksh.survey.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Builder
    public SurveyTemplate(Long id, String name, String description, List<SurveyTemplateItem> items) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
