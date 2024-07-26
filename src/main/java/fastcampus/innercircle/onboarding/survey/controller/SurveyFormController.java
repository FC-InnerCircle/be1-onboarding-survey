package fastcampus.innercircle.onboarding.survey.controller;

import fastcampus.innercircle.onboarding.survey.dto.CreateFormRequest;
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
}
