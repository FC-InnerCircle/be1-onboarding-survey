package com.example.innercircle_survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class SurveyVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "surveyVersion", cascade = CascadeType.ALL)
    private List<QuestionVersion> questionVersions;

    // Response -> SurveyVersion 의 단방향 관계만 설정한다
//    @OneToMany(mappedBy = "surveyVersion", cascade = CascadeType.ALL)
//    private List<Response> responses;

    @Column
    private int versionNumber;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDateTime createdAt;
}
