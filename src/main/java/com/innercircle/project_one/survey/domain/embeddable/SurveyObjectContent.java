package com.innercircle.project_one.survey.domain.embeddable;

import com.innercircle.project_one.survey.api.dto.SurveyObjectDTO;
import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;

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

    public SurveyObjectContent(SurveyObjectDTO objectDTO) {
        this.title = objectDTO.title();
        this.description = objectDTO.description();
        this.isRequired = objectDTO.isRequired();
    }

}