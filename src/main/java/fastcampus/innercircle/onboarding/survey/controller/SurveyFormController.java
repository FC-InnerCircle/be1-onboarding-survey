package fastcampus.innercircle.onboarding.survey.controller;

import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import fastcampus.innercircle.onboarding.survey.dto.request.CreateFormRequest;
import fastcampus.innercircle.onboarding.survey.dto.response.FindFormResponse;
import fastcampus.innercircle.onboarding.survey.service.SurveyFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/survey/form")
@RestController
public class SurveyFormController {
    private final SurveyFormService formService;

    @PostMapping
    public ResponseEntity<Object> createForm(@RequestBody final CreateFormRequest request) {
        UUID formId = formService.register(request.toEntity());
        return ResponseEntity.created(URI.create(String.format("/api/survey/form/%s", formId))).build();
    }

    @GetMapping("/{formId}")
    public ResponseEntity<FindFormResponse> getForm(@PathVariable(name = "formId") final UUID formId) {
        SurveyForm findForm = formService.getForm(formId);
        return ResponseEntity.ok(FindFormResponse.from(findForm));
    }
}
