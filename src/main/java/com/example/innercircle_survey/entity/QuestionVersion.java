package com.example.innercircle_survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class QuestionVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column
    private int versionNumber;

    @Column
    private String text;

    // QuestionType 은 잠깐 배제
}
