package com.innercircle.onboardingservey.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "serveys_version")
@NoArgsConstructor
@Getter
public class SurveyVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyVersionId;
    private String title;
    private String description;

    @ManyToOne
    private Survey survey;

    public SurveyVersion(
        String title,
        String description,
        Survey survey
    ) {
        this.title = title;
        this.description = description;
        this.survey = survey;
        valid();
    }

    private void valid() {
        Assert.hasText(this.title, "title must not be empty");
        Assert.hasText(this.description, "description must not be empty");
        Assert.notNull(this.survey, "survey must not be empty");
    }
}
