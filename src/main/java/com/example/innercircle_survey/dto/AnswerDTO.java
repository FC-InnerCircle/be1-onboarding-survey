package com.example.innercircle_survey.dto;

import com.example.innercircle_survey.entity.Answer;
import com.example.innercircle_survey.entity.AnswerOption;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AnswerDTO {

    private Long questionVersionId;

    private QuestionVersionDTO questionVersion;

    private List<String> options;

    public static AnswerDTO create(Answer entity) {
        return AnswerDTO.builder()
                .questionVersion(QuestionVersionDTO.create(entity.getQuestionVersion()))
                .options(entity.getAnswerOptions().stream()
                        .map(AnswerOption::getText)
                        .toList()
                )
                .build();
    }
}
