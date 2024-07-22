package lshh.be1onboardingsurvey.survey.domain;

import lombok.RequiredArgsConstructor;
import lshh.be1onboardingsurvey.common.lib.clock.Clock;
import lshh.be1onboardingsurvey.survey.domain.command.*;
import lshh.be1onboardingsurvey.survey.domain.component.SurveyRepository;
import lshh.be1onboardingsurvey.survey.domain.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepository repository;
    private final Clock clock;

    @Transactional(readOnly = true)
    public List<SurveyView> findAll() {
        return repository.findAllToView();
    }

    @Transactional(readOnly = true)
    public Optional<SurveyView> findByName(String name) {
        return repository.findByName(name)
                .map(SurveyView::of);
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
        survey.addItem(command);
        return repository.save(survey);
    }

    @Transactional
    public Result<?> addItemOption(AddSurveyItemOptionCommand command) {
        Survey survey = repository.findById(command.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        survey.updateItem(command);
        return repository.save(survey);
    }

    @Transactional
    public Result<?> updateItem(UpdateSurveyItemCommand updateSurveyItemCommand) {
        Survey survey = repository.findById(updateSurveyItemCommand.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        survey.updateItem(updateSurveyItemCommand, clock);
        return repository.save(survey);
    }

    @Transactional
    public Result<?> updateItemOption(UpdateSurveyItemOptionCommand command) {
        Survey survey = repository.findById(command.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        survey.updateItem(command, clock);
        return repository.save(survey);
    }

    @Transactional
    public Result<?> addResponse(AddSurveyResponseCommand command) {
        Survey survey = repository.findById(command.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        survey.addResponse(command);
        return repository.save(survey);
    }


    @Transactional
    public Result<?> addResponseItem(AddSurveyResponseItemCommand command) {
        Survey survey = repository.findById(command.surveyId())
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));
        survey.addResponseItem(command);
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
