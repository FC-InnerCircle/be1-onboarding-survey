package com.example.innercircle_survey.entity;

import com.example.innercircle_survey.enumeration.QuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class QuestionVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_version_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_version_id")
    private SurveyVersion surveyVersion;

    // Answer -> QuestionVersion 의 단방향 관계만 설정한다
//    @OneToMany(mappedBy = "questionVersion")
//    private List<Answer> answers;

    @Column(name = "version_number")
    private Integer versionNumber;

    // 항목 번호
    @Column(name = "question_number")
    private Integer number;

    // 항목 이름
    @Column(name = "question_text")
    private String title;

    // 항목 설명
    @Column(name = "question_description")
    private String description;

    // 항목 입력 형태
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    // 항목 필수 여부
    @Column(name = "required")
    private Boolean required;

    @OneToMany(mappedBy = "questionVersion")
    private List<QuestionVersionOption> questionVersionOptions;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
