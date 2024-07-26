package com.innercircle.survey.domain.survey.response;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "long_answer_responses")
public class LongAnswerResponse extends QuestionResponse{
    private String answerText;
}
