package com.fastcampus.innercircle.survey_api.domain.request;

import com.fastcampus.innercircle.survey_api.domain.QuestionType;

import java.util.List;

public class QuestionRequest {

    private final Long id;

    private final String title; // 항목 이름
    private final String description;   // 항목 설명
    private final QuestionType questionType;    // 항목 입력 형태
    private final boolean isRequired;   // 항목 필수 여부
    private final List<String> options; // 단일 선택 리스트, 다중 선택 리스트에서 선택 할 수 있는 후보

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public List<String> getOptions() {
        return options;
    }

    public QuestionRequest(Long id, String title, String description, QuestionType questionType, boolean isRequired, List<String> options) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questionType = questionType;
        this.isRequired = isRequired;
        this.options = options;
    }
}
