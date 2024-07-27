package fastcampus.innercircle.onboarding.survey.service;

import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import fastcampus.innercircle.onboarding.survey.domain.SurveyResult;
import fastcampus.innercircle.onboarding.survey.dto.request.CreateResultRequest;
import fastcampus.innercircle.onboarding.survey.exception.SurveyFormNotFoundException;
import fastcampus.innercircle.onboarding.survey.repository.SurveyFormRepository;
import fastcampus.innercircle.onboarding.survey.repository.SurveyResultRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SurveyResultService {
    private final SurveyFormRepository surveyFormRepository;
    private final SurveyResultRepository surveyResultRepository;

    @Transactional
    public Long register(final UUID formId, final CreateResultRequest request) {
        SurveyForm form = surveyFormRepository.findTop1ByFormId_IdOrderByFormId_VersionDesc(formId)
                .orElseThrow(() -> new SurveyFormNotFoundException(formId));

        SurveyResult saveResult = surveyResultRepository.save(request.toEntity(form));
        return saveResult.getId();
    }
}
