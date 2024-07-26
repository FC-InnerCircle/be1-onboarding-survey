package com.fastcampus.innercircle.survey_api.domain.response;

import com.fastcampus.innercircle.survey_api.domain.entity.Response;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class SurveyResponse {

    private final Long id;
    private final List<AnswerResponse> answers;
    private final String createdAt;

    public Long getId() {
        return id;
    }

    public List<AnswerResponse> getAnswers() {
        return answers;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public SurveyResponse(Long id, List<AnswerResponse> answers, String createdAt) {
        this.id = id;
        this.answers = answers;
        this.createdAt = createdAt;
    }

    public static SurveyResponse of(Response entity) {
        return new SurveyResponse(
                entity.getResponseId(),
                entity.getAnswers().stream().map(AnswerResponse::of).toList(),
                entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
        );
    }
}
