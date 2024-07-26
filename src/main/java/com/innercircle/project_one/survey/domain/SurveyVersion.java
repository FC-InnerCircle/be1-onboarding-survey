package com.innercircle.project_one.survey.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SurveyVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long version;

    @OneToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;


    public SurveyVersion(Long version, Survey survey) {
        this.version = version;
        this.survey  = survey;
    }

    public void setVersion(Long version){
        this.version = version;
    }

}

