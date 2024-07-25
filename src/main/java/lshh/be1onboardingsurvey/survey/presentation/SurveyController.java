package lshh.be1onboardingsurvey.survey.presentation;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lshh.be1onboardingsurvey.survey.domain.command.*;
import lshh.be1onboardingsurvey.survey.domain.SurveyService;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyResponseDetailView;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyResponseView;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyView;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService service;

    @Operation(summary = "설문지 생성")
    @PostMapping("")
    public Response<?> create(@RequestBody CreateSurveyCommand command) {
        Result<?> result = service.create(command);
        return Response.of(result);
    }

    @Operation(summary = "설문지 항목 추가")
    @PostMapping("/item")
    public Response<?> addItem(@RequestBody AddSurveyItemCommand command) {
        Result<?> result = service.addItem(command);
        return Response.of(result);
    }

    @Operation(summary = "설문지 항목 옵션 추가")
    @PostMapping("/{surveyId}/item/{itemId}/option")
    public Response<?> addOption(@RequestBody AddSurveyItemOptionCommand command) {
        Result<?> result = service.addItemOption(command);
        return Response.of(result);
    }

    @Operation(summary = "설문지 항목 수정")
    @PostMapping("/{surveyId}/item/{item}")
    public Response<?> updateItem(@RequestBody UpdateSurveyItemCommand command) {
        Result<?> result = service.updateItem(command);
        return Response.of(result);
    }

    @Operation(summary = "설문지 항목 옵션 수정")
    @PostMapping("/{surveyId}/item/{itemId}/option/{optionId}")
    public Response<?> updateOption(@RequestBody UpdateSurveyItemOptionCommand command) {
        Result<?> result = service.updateItemOption(command);
        return Response.of(result);
    }

    @Operation(summary = "설문지 응답 시작")
    @PostMapping("/{surveyId}/response")
    public Response<?> addResponse(@RequestBody BeginSurveyResponseCommand command) {
        Result<?> result = service.beginResponse(command);
        return Response.of(result);
    }

    @Operation(summary = "설문지 응답 항목 추가 - text")
    @PostMapping("/{surveyId}/response/{responseId}/text")
    public Response<?> addResponseItemText(@RequestBody AddSurveyResponseTextItemCommand command) {
        AddSurveyResponseItemCommand _command = AddSurveyResponseItemCommand.of(command);
        Result<?> result = service.addResponseItem(_command);
        return Response.of(result);
    }
    @Operation(summary = "설문지 응답 항목 추가 - textarea")
    @PostMapping("/{surveyId}/response/{responseId}/textarea")
    public Response<?> addResponseItemTextarea(@RequestBody AddSurveyResponseTextAreaItemCommand command) {
        AddSurveyResponseItemCommand _command = AddSurveyResponseItemCommand.of(command);
        Result<?> result = service.addResponseItem(_command);
        return Response.of(result);
    }
    @Operation(summary = "응답 항목 추가 - radio")
    @PostMapping("/{surveyId}/response/{responseId}/radio")
    public Response<?> addResponseItemRadio(@RequestBody AddSurveyResponseRadioItemCommand command) {
        AddSurveyResponseItemCommand _command = AddSurveyResponseItemCommand.of(command);
        Result<?> result = service.addResponseItem(_command);
        return Response.of(result);
    }
    @Operation(summary = "응답 항목 추가 - checkbox")
    @PostMapping("/{surveyId}/response/{responseId}/checkbox")
    public Response<?> addResponseItemCheckbox(@RequestBody AddSurveyResponseCheckboxItemCommand command) {
        AddSurveyResponseItemCommand _command = AddSurveyResponseItemCommand.of(command);
        Result<?> result = service.addResponseItem(_command);
        return Response.of(result);
    }

    @Operation(summary = "응답 제출")
    @PostMapping("/{surveyId}/response/{responseId}/submit")
    public Response<?> submitResponse(SubmitResponseCommand command) {
        Result<?> result = service.submitResponse(command);
        return Response.of(result);
    }

    @Operation(summary = "설문지 목록 조회")
    @GetMapping("all")
    public List<SurveyView> all(){
        return service.findAll();
    }

    @Operation(summary = "응답 조회")
    @GetMapping("/{surveyId}/response")
    public List<SurveyResponseView> response(@PathVariable Long surveyId){
        return service.findResponses(surveyId);
    }

    @Operation(summary = "응답 상세 조회")
    @GetMapping("/{surveyId}/response/detail")
    public List<SurveyResponseDetailView> responseDetail(@PathVariable Long surveyId){
        return service.findResponseDetails(surveyId);
    }


}
