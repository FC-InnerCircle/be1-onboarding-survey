package org.ksh.survey.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ksh.survey.model.SurveyUpdateRequest;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    private String name;
    private String description;

    @Builder
    public SurveyTemplate(Long id, String name, String description, List<SurveyTemplateItem> items) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void update(SurveyUpdateRequest surveySaveRequest) {
        this.name = surveySaveRequest.getSurveyName();
        this.description = surveySaveRequest.getSurveyDescription();
    }
}
