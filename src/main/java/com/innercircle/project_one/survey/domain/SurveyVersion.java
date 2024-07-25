package com.innercircle.project_one.survey.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Getter
@Data
public class SurveyVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int version;

    @OneToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

}

