package com.innercircle.survey.interfaces.controller.survey;

import com.innercircle.survey.domain.survey.SurveyService;
import com.innercircle.survey.interfaces.common.dto.ApiResultResponse;
import com.innercircle.survey.interfaces.controller.survey.dto.SurveyDto;
import com.innercircle.survey.interfaces.controller.survey.dto.SurveyResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "설문조사", description = "Survey-controller")
@RequiredArgsConstructor
@RequestMapping("/v1/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    // 설문 등록
    @PostMapping
    public ApiResultResponse<SurveyDto.Response> registerSurvey(
            @RequestBody @Valid SurveyDto.Request request) {
        return ApiResultResponse.ok(
                SurveyDto.Response.of(surveyService.registerSurvey(request.toCreateCommand())));
    }

    // 설문 조회
    @GetMapping
    public ApiResultResponse<List<SurveyDto.Response>> getSurveys() {
        return ApiResultResponse.ok(
                surveyService.getSurveys().stream()
                        .map(SurveyDto.Response::of)
                        .toList());
    }


    @PatchMapping
    public ApiResultResponse<SurveyDto.Response> editSurvey() {
        return null;
    }

    @PostMapping("/submit")
    public ApiResultResponse<List<SurveyResponseDto.Response>> submitSurveyResponse(
            @RequestBody @Valid SurveyResponseDto.Request request) {

        return ApiResultResponse.ok(
                surveyService.submitSurvey(request.toCreateCommand()).stream()
                        .map(SurveyResponseDto.Response::of)
                        .toList());

    }

    @GetMapping("/submit/{surveyId}")
    public ApiResultResponse<SurveyResponseDto.Response> submitSurvey(@PathVariable("surveyId") @NotNull
                                                                      String surveyId) {
        return null;
    }
}
