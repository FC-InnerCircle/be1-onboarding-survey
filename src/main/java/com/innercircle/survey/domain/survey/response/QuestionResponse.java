package com.innercircle.survey.domain.survey.response;

import com.innercircle.survey.domain.AbstractEntity;
import com.innercircle.survey.domain.survey.question.SurveyQuestion;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "question_responses")
@Inheritance(strategy = InheritanceType.JOINED)
public class QuestionResponse extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "response_id")
    private SurveyResponse response;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private SurveyQuestion question;
}
