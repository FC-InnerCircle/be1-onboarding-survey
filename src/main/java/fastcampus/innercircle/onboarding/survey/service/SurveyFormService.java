package fastcampus.innercircle.onboarding.survey.service;

import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import fastcampus.innercircle.onboarding.survey.repository.SurveyFormRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SurveyFormService {
    private final SurveyFormRepository surveyFormRepository;

    @Transactional
    public Long register(final SurveyForm form) {
        SurveyForm saveForm = surveyFormRepository.save(form);
        return saveForm.getId();
    }
}
