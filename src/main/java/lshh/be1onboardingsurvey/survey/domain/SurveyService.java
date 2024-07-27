package lshh.be1onboardingsurvey.survey.domain;

import lombok.RequiredArgsConstructor;
import lshh.be1onboardingsurvey.common.lib.cache.lock.AdvisoryLock;
import lshh.be1onboardingsurvey.common.lib.clock.Clock;
import lshh.be1onboardingsurvey.survey.domain.command.*;
import lshh.be1onboardingsurvey.survey.domain.component.SurveyReader;
import lshh.be1onboardingsurvey.survey.domain.component.SurveyRepository;
import lshh.be1onboardingsurvey.survey.domain.component.SurveyResponseBuffer;
import lshh.be1onboardingsurvey.survey.domain.dto.*;
import lshh.be1onboardingsurvey.survey.domain.entity.Survey;
import lshh.be1onboardingsurvey.survey.domain.entity.SurveyItem;
import lshh.be1onboardingsurvey.survey.domain.entity.SurveyResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final SurveyReader reader;
    private final SurveyRepository repository;
    private final Clock clock;
    private final SurveyResponseBuffer responseBuffer;

    @Transactional(readOnly = true)
    public List<SurveyView> findAll() {
        return reader.findAllToView();
    }

    @Transactional(readOnly = true)
    public List<SurveyView> findByName(String name) {
        return repository.findByName(name).stream()
                .map(SurveyView::of).toList();
    }

    @Transactional(readOnly = true)
    public Optional<SurveyAllVersionView> findWithAllVersion(Long id) {
        return repository.findById(id)
                .map(SurveyAllVersionView::of);
    }

    @Transactional
    public Result<?> create(CreateSurveyCommand command) {
        Survey survey = Survey.of(command);
        return repository.save(survey);
    }

    @Transactional
    public Result<?> addItem(AddSurveyItemCommand command) {
        Survey survey = repository.findById(command.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        survey.addItem(command, clock.now());
        return repository.save(survey);
    }

    @Transactional
    public Result<?> addItemOption(AddSurveyItemOptionCommand command) {
        Survey survey = repository.findById(command.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        survey.updateItem(command, clock.now());
        return repository.save(survey);
    }

    @AdvisoryLock(prekey = "updateItem", key = "#updateSurveyItemCommand.itemId()")
    @Transactional
    public Result<?> updateItem(UpdateSurveyItemCommand updateSurveyItemCommand) {
        Survey survey = repository.findById(updateSurveyItemCommand.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        survey.updateItem(updateSurveyItemCommand, clock);
        return repository.save(survey);
    }

    @Transactional
    @AdvisoryLock(prekey ="updateItemOption", key = "#command.optionId()")
    public Result<?> updateItemOption(UpdateSurveyItemOptionCommand command) {
        Survey survey = repository.findById(command.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        survey.updateItem(command, clock.now());
        return repository.save(survey);
    }

    @Transactional
    public Result<?> beginResponse(BeginSurveyResponseCommand command) {
        Survey survey = repository.findById(command.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        SurveyResponse response = command.toEntity();
        response.setSurvey(survey);
        return responseBuffer.save(response);
    }

    @Transactional
    public Result<?> addResponseItem(AddSurveyResponseItemCommand command) {
        SurveyResponse response = responseBuffer.findResponseById(command.responseId())
                .orElseThrow(() -> new IllegalArgumentException("Survey response not found"));
        Survey survey = repository.findById(command.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        SurveyItem item = survey.findItem(command.itemId())
                .orElseThrow(() -> new IllegalArgumentException("Survey item not found"));
        response.addItem(command, item.getFormType());
        return repository.save(survey);
    }

    @Transactional
    public Result<?> submitResponse(SubmitResponseCommand command) {
        SurveyResponse surveyResponse = responseBuffer.findResponseById(command.responseId())
                .orElseThrow(() -> new IllegalArgumentException("Survey response not found"));
        Survey survey = repository.findById(surveyResponse.getSurvey().getId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        surveyResponse.setSubmitted(clock.now());
        survey.addResponse(surveyResponse);
        return repository.save(survey);
    }

    @Transactional(readOnly = true)
    public List<SurveyResponseView> findResponses(Long surveyId) {
        return repository.findResponseViewBySurveyId(surveyId);
    }

    @Transactional(readOnly = true)
    public List<SurveyResponseDetailView> findResponseDetails(Long surveyId) {
        Survey survey = repository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        return survey.getResponses().stream()
                .map(SurveyResponseDetailView::of)
                .toList();
    }
}
