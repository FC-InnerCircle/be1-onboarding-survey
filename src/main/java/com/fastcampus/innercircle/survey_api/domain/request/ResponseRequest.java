package com.fastcampus.innercircle.survey_api.domain.request;

import java.util.ArrayList;
import java.util.List;

public class ResponseRequest {

    private final List<AnswerRequest> answers = new ArrayList<>();

    public List<AnswerRequest> getAnswers() {
        return answers;
    }
}
