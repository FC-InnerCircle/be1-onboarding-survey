package com.psh10066.survey.survey_answer.adapter.in.web.response;

import com.psh10066.survey.survey_answer.adapter.in.web.response.dto.FindSurveyAnswerResponseDto;
import com.psh10066.survey.survey_answer.domain.SurveyAnswer;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record FindSurveyAnswersResponse(
    @Schema(description = "설문조사 응답 목록", requiredMode = Schema.RequiredMode.REQUIRED)
    List<FindSurveyAnswerResponseDto> responses
) {

    public static FindSurveyAnswersResponse from(List<SurveyAnswer> surveyAnswers) {
        return new FindSurveyAnswersResponse(FindSurveyAnswerResponseMapper.INSTANCE.toDto(surveyAnswers));
    }
}
