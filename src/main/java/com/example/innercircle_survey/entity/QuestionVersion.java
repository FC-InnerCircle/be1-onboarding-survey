package com.example.innercircle_survey.entity;

import com.example.innercircle_survey.dto.QuestionVersionDTO;
import com.example.innercircle_survey.enumeration.QuestionType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * QuestionVersion : SurveyVersion 은 N:M이 아닌 N:1로 본다
 * 하나의 Question 은 계속 재활용되는 것이 아니라 등록되는 시점을 유일한 사용으로 봄
 *
 * Question 과 SurveyVersion 이 가진 List<QuestionVersion>은 서로 다르다.
 * Question 이 가진 것은 한 Question 의 버전 관리 용도,
 * SurveyVersion 이 가진 것은 해당 설문을 구성하는 각 Question 의 최신 버전으로 본다.
 * */

@Entity
@Getter @Setter
@Builder
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_version_seq")
    @SequenceGenerator(name = "question_version_seq", sequenceName = "QUESTION_VERSION_SEQ", allocationSize = 1)
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

    // 삭제된 질문은 invalid 로 두고 노출 금지
    @Column(name = "valid")
    private Boolean valid;

    @OneToMany(mappedBy = "questionVersion", cascade = CascadeType.ALL)
    private List<QuestionVersionOption> questionVersionOptions;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public static QuestionVersion create(QuestionVersionDTO dto) {
        QuestionVersion questionVersion = QuestionVersion.builder()
                .number(dto.getNumber())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .type(dto.getType())
                .required(dto.getRequired())
                .valid(dto.getValid())
                .build();

        List<QuestionVersionOption> options = dto.getOptions().stream()
                .map(text -> {
                    QuestionVersionOption option = QuestionVersionOption.create(text);
                    option.setQuestionVersion(questionVersion);
                    return option;
                })
                .toList();

        questionVersion.setQuestionVersionOptions(options);
        questionVersion.setQuestion(new Question());

        return questionVersion;
    }
}
