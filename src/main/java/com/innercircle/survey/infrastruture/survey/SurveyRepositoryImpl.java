package com.innercircle.survey.infrastruture.survey;

import com.innercircle.survey.domain.survey.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SurveyRepositoryImpl implements SurveyRepository {

    private final SurveyJpaRepository surveyJpaRepository;
    private final SurveyItemJpaRepository surveyItemJpaRepository;
    private final SelectOptionJpaRepository selectOptionJpaRepository;
    private final SurveyResponseJpaRepository surveyResponseJpaRepository;
    private final SurveyResponseDetailJpaRepository surveyResponseDetailJpaRepository;


    @Override
    public Optional<Survey> saveSurvey(Survey survey) {

        SurveyEntity surveyEntity = surveyJpaRepository.save(SurveyEntity.toEntity(survey));

        return Optional.of(surveyEntity.toDomain());
    }

    @Override
    public Optional<SurveyItem> saveSurveyItem(SurveyItem surveyItem) {
        SurveyItemEntity surveyItemEntity = surveyItemJpaRepository.save(SurveyItemEntity.toEntity(surveyItem));

        return Optional.of(surveyItemEntity.toDomain());
    }


    @Override
    public List<SelectOption> saveSelectOptions(List<SelectOption> selectOptions) {

        return selectOptionJpaRepository.saveAll(
                        selectOptions.stream()
                                .map(SelectOptionEntity::toEntity)
                                .toList())
                .stream()
                .map(SelectOptionEntity::toDomain)
                .toList();

    }

    @Override
    public void deleteAll() {
        surveyJpaRepository.deleteAllInBatch();
        surveyItemJpaRepository.deleteAllInBatch();
        selectOptionJpaRepository.deleteAllInBatch();
        surveyResponseJpaRepository.deleteAllInBatch();
        surveyResponseDetailJpaRepository.deleteAllInBatch();

    }

    @Override
    public List<Survey> getSurveys() {
        return surveyJpaRepository.findAll().stream().map(SurveyEntity::toDomain).toList();
    }


    @Override
    public List<SurveyItem> getSurveyItems(Long surveyId) {
        return surveyItemJpaRepository.findAllBySurvey_surveyId(surveyId)
                .stream()
                .map(SurveyItemEntity::toDomain)
                .toList();
    }

    @Override
    public List<SurveyItem> getSurveyItems() {
        return surveyItemJpaRepository.findAll().stream().map(SurveyItemEntity::toDomain).toList();
    }

    @Override
    public List<SelectOption> getSelectOptions(Long surveyItemId) {
        return selectOptionJpaRepository.findAllBySurveyItem_surveyItemId(surveyItemId)
                .stream()
                .map(SelectOptionEntity::toDomain)
                .toList();
    }

    @Override
    public List<SurveyResponse> saveSurveyResponses(List<SurveyResponse> surveyResponses) {
        return surveyResponseJpaRepository.saveAll(
                        surveyResponses.stream()
                                .map(SurveyResponseEntity::toEntity)
                                .toList())
                .stream()
                .map(SurveyResponseEntity::toDomain)
                .toList();


    }

    @Override
    public List<SurveyResponseDetail> saveSurveyResponseDetails(List<SurveyResponseDetail> responseDetails) {
        return surveyResponseDetailJpaRepository.saveAll(
                        responseDetails.stream()
                                .map(SurveyResponseDetailEntity::toEntity)
                                .toList())
                .stream()
                .map(SurveyResponseDetailEntity::toDomain)
                .toList();


    }

}
