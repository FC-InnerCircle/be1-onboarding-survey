package com.example.innercircle_survey.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 세부 사항들은 모두 SurveyVersion 에서 관리
 * Survey 는 식별자 및 연관계만 갖도록 한다
 * */

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_id")
    private Long id;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<SurveyVersion> surveyVersions;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Transient
    private SurveyVersion recentVersion;

    public static Survey create(SurveyVersion surveyVersion) {
        Survey survey = new Survey();
        survey.setRecentVersion(surveyVersion);
        surveyVersion.setSurvey(survey);  // 관계의 주인에서 관계 매핑 위함. 너무 억지스럽다...
        survey.setSurveyVersions(new ArrayList<>(List.of(surveyVersion)));
        return survey;
    }
}
