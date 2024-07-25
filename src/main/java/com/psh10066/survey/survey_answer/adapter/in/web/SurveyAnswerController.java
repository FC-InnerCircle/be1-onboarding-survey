package com.psh10066.survey.survey_answer.adapter.in.web;

import com.psh10066.survey.survey_answer.adapter.in.web.request.RegisterSurveyAnswerRequest;
import com.psh10066.survey.survey_answer.adapter.in.web.response.RegisterSurveyAnswerResponse;
import com.psh10066.survey.survey_answer.application.port.in.RegisterSurveyAnswerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SurveyAnswerController {

    private final RegisterSurveyAnswerUseCase registerSurveyAnswerUseCase;

    @Operation(summary = "설문조사 응답 제출 API")
    @PostMapping("/survey-answer/register")
    public RegisterSurveyAnswerResponse registerAnswer(@RequestBody RegisterSurveyAnswerRequest request) {
        Long answerId = registerSurveyAnswerUseCase.register(request.toCommand());
        return new RegisterSurveyAnswerResponse(answerId);
    }
}
