package com.psh10066.survey.survey_answer.adapter.in.web;

import com.psh10066.survey.survey_answer.adapter.in.web.request.RegisterSurveyAnswerRequest;
import com.psh10066.survey.survey_answer.adapter.in.web.response.FindSurveyAnswersResponse;
import com.psh10066.survey.survey_answer.adapter.in.web.response.RegisterSurveyAnswerResponse;
import com.psh10066.survey.survey_answer.application.port.in.FindSurveyAnswerUseCase;
import com.psh10066.survey.survey_answer.application.port.in.RegisterSurveyAnswerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SurveyAnswerController {

    private final FindSurveyAnswerUseCase findSurveyAnswerUseCase;
    private final RegisterSurveyAnswerUseCase registerSurveyAnswerUseCase;

    @Operation(summary = "설문조사 응답 제출 API")
    @PostMapping("/survey-answer/register")
    public RegisterSurveyAnswerResponse registerAnswer(@RequestBody RegisterSurveyAnswerRequest request) {
        Long answerId = registerSurveyAnswerUseCase.register(request.toCommand());
        return new RegisterSurveyAnswerResponse(answerId);
    }

    @Operation(summary = "설문조사 응답 조회 API")
    @GetMapping("/survey-answer/{surveyId}")
    public FindSurveyAnswersResponse findAnswers(@Parameter(description = "설문조사 ID") @PathVariable UUID surveyId) {
        return FindSurveyAnswersResponse.from(findSurveyAnswerUseCase.findAnswers(surveyId));
    }
}
