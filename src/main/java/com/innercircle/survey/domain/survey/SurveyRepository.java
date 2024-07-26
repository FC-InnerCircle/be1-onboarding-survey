package com.innercircle.survey.domain.survey;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepository {
    Optional<Survey> saveSurvey(Survey survey);

    Optional<SurveyItem> saveSurveyItem(SurveyItem surveyItem);

    List<SelectOption> saveSelectOptions(List<SelectOption> selectOptions);

    void deleteAll();

    List<Survey> getSurveys();

    List<SurveyItem> getSurveyItems(Long surveyId);

    List<SurveyItem> getSurveyItems();

    List<SelectOption> getSelectOptions(Long surveyItemId);

    List<SurveyResponse> saveSurveyResponses(List<SurveyResponse> surveyResponses);

    List<SurveyResponseDetail> saveSurveyResponseDetails(List<SurveyResponseDetail> responseDetails);
}
