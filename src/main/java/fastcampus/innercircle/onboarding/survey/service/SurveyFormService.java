package fastcampus.innercircle.onboarding.survey.service;

import fastcampus.innercircle.onboarding.survey.domain.FormId;
import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import fastcampus.innercircle.onboarding.survey.exception.SurveyFormNotFoundException;
import fastcampus.innercircle.onboarding.survey.exception.SurveyFormUnchangedException;
import fastcampus.innercircle.onboarding.survey.repository.SurveyFormRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SurveyFormService {
    private final SurveyFormRepository surveyFormRepository;

    @Transactional
    public synchronized UUID register(final SurveyForm form) {
        SurveyForm saveForm = surveyFormRepository.save(form);
        return saveForm.getId();
    }

    @Transactional
    public synchronized UUID update(final UUID formId, final SurveyForm changedForm) {
        SurveyForm beforeForm = surveyFormRepository.findTop1ByFormId_IdOrderByFormId_VersionDesc(formId)
                .orElseThrow(() -> new SurveyFormNotFoundException(formId));

        if (beforeForm.isUnchanged(changedForm)) {
            throw new SurveyFormUnchangedException();
        }

        changedForm.setNextFormId(beforeForm);
        SurveyForm saveForm = surveyFormRepository.save(changedForm);

        return saveForm.getId();
    }

    public SurveyForm getForm(final UUID formId) {
        Optional<SurveyForm> findLastVersionForm = surveyFormRepository.findTop1ByFormId_IdOrderByFormId_VersionDesc(formId);
        return findLastVersionForm.orElseThrow(
                () -> new SurveyFormNotFoundException(formId));
    }
}
