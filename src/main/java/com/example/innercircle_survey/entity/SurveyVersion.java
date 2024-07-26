package com.example.innercircle_survey.entity;

import com.example.innercircle_survey.dto.SurveyVersionDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@Builder
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

    @OneToMany(mappedBy = "surveyVersion", cascade = CascadeType.ALL)
    private List<Response> responses;

    @Column(name = "version_number")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "survey_version_seq")
    @SequenceGenerator(name = "survey_version_seq", sequenceName = "SURVEY_VERSION_SEQ", allocationSize = 1)
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


    public static SurveyVersion create(SurveyVersionDTO dto) {
        // 반대쪽에서 세팅할 수 있도록 일단은 생성
        SurveyVersion surveyVersion = SurveyVersion.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();

        // 연관관계 주인이 QuestionVersion 이기 때문에 매핑은 반드시 저쪽에서 이뤄져야 함
        // 너무 더러운데.... 다른 방법은 없나?

        List<QuestionVersion> questions = dto.getQuestions().stream()
                .map(questionDTO -> {
                    QuestionVersion questionVersion = QuestionVersion.create(questionDTO);
                    questionVersion.setSurveyVersion(surveyVersion);
                    return questionVersion;
                })
                .toList();

        surveyVersion.setQuestionVersions(questions);

        return surveyVersion;
    }
}
