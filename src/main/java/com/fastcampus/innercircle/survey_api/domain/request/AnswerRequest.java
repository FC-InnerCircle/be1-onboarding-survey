package com.fastcampus.innercircle.survey_api.domain.request;

public class AnswerRequest {

    private final Long questionId;  // 설문 받을 항목
    private final String content;   // 응답 값

    public Long getQuestionId() {
        return questionId;
    }

    public String getContent() {
        return content;
    }

    public AnswerRequest(Long questionId, String content) {
        this.questionId = questionId;
        this.content = content;
    }
}
