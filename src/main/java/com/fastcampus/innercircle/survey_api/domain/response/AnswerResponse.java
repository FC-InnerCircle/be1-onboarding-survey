package com.fastcampus.innercircle.survey_api.domain.response;

import com.fastcampus.innercircle.survey_api.domain.QuestionType;
import com.fastcampus.innercircle.survey_api.domain.entity.Answer;

public class AnswerResponse {

    private final Long answerId;
    private final String questionTitle;
    private final String questionDescription;
    private final QuestionType questionType;
    private final boolean isRequired;
    private final String content;

    public Long getAnswerId() {
        return answerId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public String getContent() {
        return content;
    }

    public AnswerResponse(Long answerId, String questionTitle, String questionDescription, QuestionType questionType, boolean isRequired, String content) {
        this.answerId = answerId;
        this.questionTitle = questionTitle;
        this.questionDescription = questionDescription;
        this.questionType = questionType;
        this.isRequired = isRequired;
        this.content = content;
    }

    public static AnswerResponse of(Answer entity) {
        return new AnswerResponse(
                entity.getAnswerId(),
                entity.getQuestionTitle(),
                entity.getQuestionDescription(),
                entity.getQuestionType(),
                entity.isRequired(),
                entity.getContent()
        );
    }
}
