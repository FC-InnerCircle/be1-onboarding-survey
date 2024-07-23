package com.example.innercircle_survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class SurveyVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "survey_version_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "surveyVersion", cascade = CascadeType.ALL)
    private List<QuestionVersion> questionVersions;

    // Response -> SurveyVersion 의 단방향 관계만 설정한다
//    @OneToMany(mappedBy = "surveyVersion", cascade = CascadeType.ALL)
//    private List<Response> responses;

    @Column(name = "version_number")
    private Integer versionNumber;

    @Column(name = "survey_title")
    private String title;

    @Lob
    @Column(name = "survey_description")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
