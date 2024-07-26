package org.ksh.survey.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.ksh.survey.model.SurveyUpdateRequest;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SurveyTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    private Long version;

    private String name;
    private String description;

    @Builder
    public SurveyTemplate(Long id, Long version, String name, String description) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.description = description;
    }

    public void update(SurveyUpdateRequest surveySaveRequest, Long version) {
        this.name = surveySaveRequest.getSurveyName();
        this.description = surveySaveRequest.getSurveyDescription();
        this.version = version + 1;
    }

}
