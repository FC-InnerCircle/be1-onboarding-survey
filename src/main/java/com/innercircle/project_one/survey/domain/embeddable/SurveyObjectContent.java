package com.innercircle.project_one.survey.domain.embeddable;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Embeddable
@NoArgsConstructor
public class SurveyObjectContent {
    private String title;
    private String description;
    private boolean isRequired;

    public SurveyObjectContent(String title, String description, boolean isRequired) {
        this.title = title;
        this.description = description;
        this.isRequired = isRequired;
    }


}