package com.innercircle.survey.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * 선택 리스트 값들을 가지고 있을 엔티티<br>
 * [항목 입력 형태]가 [단일 선택 리스트], [다중 선택 리스트]인 경우에만 사용됨
 */
@Getter
@NoArgsConstructor
@Entity
public class QuestionOption extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    private Integer questionOptionOrder;

    private String questionOptionValue;

    private Integer version;

    @Builder
    private QuestionOption(Question question, Integer questionOptionOrder, String questionOptionValue, Integer version) {
        this.question = question;
        this.questionOptionOrder = questionOptionOrder;
        this.questionOptionValue = questionOptionValue;
        this.version = version;
    }
}
