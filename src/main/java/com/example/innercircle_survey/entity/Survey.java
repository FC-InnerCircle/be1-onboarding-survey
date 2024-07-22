package com.example.innercircle_survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 세부 사항들은 모두 SurveyVersion 에서 관리
 * Survey 는 식별자 및 연관계만 갖도록 한다
 * */

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<SurveyVersion> surveyVersions;
}
