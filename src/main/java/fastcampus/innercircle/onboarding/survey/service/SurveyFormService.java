package fastcampus.innercircle.onboarding.survey.service;

import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import fastcampus.innercircle.onboarding.survey.exception.SurveyFormNotFoundException;
import fastcampus.innercircle.onboarding.survey.repository.SurveyFormRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SurveyFormService {
    private final SurveyFormRepository surveyFormRepository;

    @Transactional
    public Long register(final SurveyForm form) {
        SurveyForm saveForm = surveyFormRepository.save(form);
        return saveForm.getId();
    }

    public SurveyForm getForm(final Long formId) {
        Optional<SurveyForm> findLastVersionForm = surveyFormRepository.findTop1ByIdOrderByVersionDesc(formId);
        return findLastVersionForm.orElseThrow(
                () -> new SurveyFormNotFoundException(String.format("[%s]에 해당하는 폼을 찾을 수 없습니다.", formId)));
    }
}
