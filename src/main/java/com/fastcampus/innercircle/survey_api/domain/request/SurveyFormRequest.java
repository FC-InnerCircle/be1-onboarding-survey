package com.fastcampus.innercircle.survey_api.domain.request;

public class SurveyFormRequest {

    private final String title; // 설문조사 이름
    private final String description;   // 설문조사 설명

    // TODO 설문 받을 항목

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public SurveyFormRequest(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
