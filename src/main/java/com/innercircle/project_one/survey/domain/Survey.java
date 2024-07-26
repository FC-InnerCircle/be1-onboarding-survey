package com.innercircle.project_one.survey.domain;

import com.innercircle.project_one.survey.api.dto.SurveyDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @OneToOne(mappedBy = "survey", cascade = CascadeType.ALL)
    private SurveyVersion surveyVersion;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<SurveyObject> surveyObjects = new ArrayList<>();

    public Survey(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long updateSurveyVersion(SurveyVersion surveyVersion) {
        this.surveyVersion = surveyVersion;
        return surveyVersion.getVersion();
    }

    public int updateSurveyObjects(List<SurveyObject> surveyObjects) {
        this.surveyObjects = surveyObjects;
        return surveyObjects.size();
    }

    public void updateSurveyTitleAndDescription(SurveyDTO surveyDTO) {
        this.title = surveyDTO.title();
        this.description = surveyDTO.description();
    }

}
