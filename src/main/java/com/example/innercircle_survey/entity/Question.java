package com.example.innercircle_survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 세부 사항들은 모두 QuestionVersion 에서 관리
 * Question 는 식별자 및 연관계만 갖도록 한다
 * */

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionVersion> questionVersions = new ArrayList<>();

    // 최초 생성 시점
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // 버전 변경 시점
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
}
