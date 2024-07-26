package com.innercircle.survey.domain.survey;

import com.innercircle.survey.domain.survey.command.SurveyCommand;
import com.innercircle.survey.domain.survey.command.SurveyResponseCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.innercircle.survey.domain.survey.SurveyItem.ItemInputFormat.MULTIPLE_SELECTION;
import static com.innercircle.survey.domain.survey.SurveyItem.ItemInputFormat.SINGLE_SELECTION;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyValidator surveyValidator;

    @Transactional
    public Survey registerSurvey(SurveyCommand.Create command) {
        // 설문조사 등록
        Survey survey = Survey.toSurveyDomain(command);
        Survey saveSurvey = surveyValidator.checkSaveSurvey(surveyRepository.saveSurvey(survey));
        List<SurveyItem> surveyItems = new ArrayList<>();
        command.surveyItems().forEach(getSurveyItem -> {
            // 설문항목 등록
            SurveyItem surveyItem = SurveyItem.toSurveyItemDomain(getSurveyItem, saveSurvey);
            SurveyItem saveSurveyItem = surveyValidator.checkSaveSurveyItem(surveyRepository.saveSurveyItem(surveyItem));
            // 설문항목 옵션에 선택 항목 등록
            if (saveSurveyItem.getInputFormat() == SINGLE_SELECTION
                    || saveSurveyItem.getInputFormat() == MULTIPLE_SELECTION) {
                List<SelectOption> selectOptions = SelectOption.toSelectOptionDomains(getSurveyItem.selectOptions(),
                        saveSurveyItem);
                List<SelectOption> saveSelectOptions = surveyRepository.saveSelectOptions(selectOptions);
                saveSurveyItem.setSelectOptions(saveSelectOptions);
            }
            surveyItems.add(saveSurveyItem);
        });
        saveSurvey.setSurveyItem(surveyItems);
        return saveSurvey;
    }

    public List<Survey> getSurveys() {
        List<Survey> surveys = surveyRepository.getSurveys();
        List<SurveyItem> surveyItems = surveyRepository.getSurveyItems();

        surveyItems.forEach(surveyItem -> {
            List<SelectOption> selectOptions = surveyRepository.getSelectOptions(surveyItem.getSurveyItemId());
            surveyItem.setSelectOptions(selectOptions);
        });

        return surveys.stream()
                .peek(survey -> survey.setSurveyItem(
                        surveyItems.stream()
                                .filter(surveyItem -> surveyItem.getSurvey().getSurveyId().equals(survey.getSurveyId()))
                                .toList())).toList();
    }

    @Transactional
    public List<SurveyResponse> submitSurvey(SurveyResponseCommand.Create command) {
        List<SurveyResponse> surveyResponses = SurveyResponse.toSurveyResponseDomains(command);
        List<SurveyResponse> saveSurveyResponses = surveyRepository.saveSurveyResponses(surveyResponses);

        saveSurveyResponses.forEach(savedSurveyResponse -> command.surveyResponses().stream()
                .filter(getSurveyResponse -> getSurveyResponse.surveyItemId().equals(savedSurveyResponse.getSurveyItemId()))
                .filter(getSurveyResponse -> getSurveyResponse.inputFormat() == SINGLE_SELECTION ||
                        getSurveyResponse.inputFormat() == MULTIPLE_SELECTION)
                .findFirst()
                .ifPresent(getSurveyResponse -> {
                    List<SurveyResponseDetail> responseDetails = SurveyResponseDetail
                            .toResponseDetailDomains(getSurveyResponse.answerOptions(), savedSurveyResponse);
                    List<SurveyResponseDetail> savedResponseDetails = surveyRepository.saveSurveyResponseDetails(responseDetails);
                    savedSurveyResponse.setResponseDetail(savedResponseDetails);
                }));

        return saveSurveyResponses;
    }
}
