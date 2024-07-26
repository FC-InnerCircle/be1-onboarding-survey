package fastcampus.innercircle.onboarding.survey.controller;

import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import fastcampus.innercircle.onboarding.survey.dto.request.CreateFormRequest;
import fastcampus.innercircle.onboarding.survey.dto.response.FindFormResponse;
import fastcampus.innercircle.onboarding.survey.service.SurveyFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/survey/form")
@RestController
public class SurveyFormController {
    private final SurveyFormService formService;

    @PostMapping
    public ResponseEntity<Object> createForm(@RequestBody final CreateFormRequest request) {
        Long formId = formService.register(request.toEntity());
        return ResponseEntity.created(URI.create(String.format("/api/survey/form/%d", formId))).build();
    }

    @GetMapping("/{formId}")
    public ResponseEntity<FindFormResponse> getForm(@PathVariable(name = "formId") Long formId) {
        SurveyForm findForm = formService.getForm(formId);
        return ResponseEntity.ok(FindFormResponse.from(findForm));
    }
}
