package com.fastcampus.innercircle.survey_api.domain.request;

import java.util.ArrayList;
import java.util.List;

public class SurveyFormRequest {

    private final String title; // 설문조사 이름
    private final String description;   // 설문조사 설명

    // 설문 받을 항목
    private final List<QuestionRequest> questions = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<QuestionRequest> getQuestions() {
        return questions;
    }

    public SurveyFormRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
