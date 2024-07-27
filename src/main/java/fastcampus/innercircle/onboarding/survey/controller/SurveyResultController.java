package fastcampus.innercircle.onboarding.survey.controller;

import fastcampus.innercircle.onboarding.survey.dto.request.CreateResultRequest;
import fastcampus.innercircle.onboarding.survey.service.SurveyResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/survey/form/{formId}/result")
@RestController
public class SurveyResultController {
    private final SurveyResultService surveyResultService;

    @PostMapping
    public ResponseEntity<Object> submitResponse(@PathVariable(name = "formId") final UUID formId, @RequestBody final CreateResultRequest request) {
        Long resultId = surveyResultService.register(formId, request);
        return ResponseEntity.created(URI.create(String.format("/api/survey/form/%s/result/%d", formId, resultId))).build();
    }
}
