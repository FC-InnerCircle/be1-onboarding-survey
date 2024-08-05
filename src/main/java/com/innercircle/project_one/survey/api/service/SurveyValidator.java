package com.innercircle.project_one.survey.api.service;

import com.innercircle.project_one.survey.api.dto.SurveySubmitDTO;
import com.innercircle.project_one.survey.api.repository.*;
import com.innercircle.project_one.survey.common.SurveyObjectDataType;
import com.innercircle.project_one.survey.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class SurveyValidator {

    private final ElementObjectRepository elementObjectRepository;

    private final SurveyService surveyService;


    public void validateUpdateable(Long requestId, Survey survey) {
        validateUpdateVersion(requestId, survey);
    }
    public void validateSubmitable(Survey survey, SurveySubmitDTO surveySubmitDTO) {
        validateSubmitVersion(survey, surveySubmitDTO);
        validateSurveyObjects(survey, surveySubmitDTO);
    }

    private void validateSubmitVersion(Survey survey, SurveySubmitDTO surveySubmitDTO) {
        if(!Objects.equals(survey.getSurveyVersion().getVersion(), surveySubmitDTO.version())) {
            throw new IllegalArgumentException("버전이 일치하지 않습니다.");
        }
    }

    private void validateUpdateVersion(Long requestId, Survey survey) {
        if(!Objects.equals(requestId, survey.getSurveyVersion().getVersion())) {
            throw new IllegalArgumentException("버전이 일치하지 않습니다.");
        }
    }

    private void validateSurveyObjects(Survey survey, SurveySubmitDTO surveySubmitDTO) {
        List<SurveyObject> savedSurveyObjects = surveyService.getSurveyVersionObjects(survey.getId(), surveySubmitDTO);
        List<SurveySubmitDTO.SurveySubmitObject> submitSurveyObjects = surveySubmitDTO.objects();

        if (savedSurveyObjects.size() != submitSurveyObjects.size()) {
            throw new IllegalArgumentException("목록이 일치하지 않습니다.");
        }

        for (int i = 0; i < savedSurveyObjects.size(); i++) {
            SurveyObject surveyObject = savedSurveyObjects.get(i);
            SurveySubmitDTO.SurveySubmitObject submitSurveyObject = submitSurveyObjects.get(i);

            validateRequired(surveyObject, submitSurveyObject);
            validateObjectOrder(surveyObject, submitSurveyObject);
            validateObjectType(surveyObject, submitSurveyObject);
        }
    }

    private void validateRequired(SurveyObject surveyObject, SurveySubmitDTO.SurveySubmitObject submitSurveyObject) {
        if(surveyObject.getSurveyObjectContent().isRequired()
                && submitSurveyObject.content().isEmpty()){
            throw new IllegalArgumentException("필수값을 할당해야 합니다.");
        }
    }

    private void validateObjectOrder(SurveyObject surveyObject, SurveySubmitDTO.SurveySubmitObject submitSurveyObject) {
        if(!Objects.equals(surveyObject.getId(), submitSurveyObject.id())){
            throw new IllegalArgumentException("목록 순서가 일치하지 않습니다.");
        }
    }

    private void validateObjectType(SurveyObject surveyObject, SurveySubmitDTO.SurveySubmitObject submitSurveyObject) {
        if (surveyObject.getType() != SurveyObjectDataType.of(submitSurveyObject.type())) {
            throw new IllegalArgumentException("목록 타입이 일치하지 않습니다.");
        }
    }

    /**
     * 제출한 요소가 기존 오브젝트 리스트 안에 포함되는지 검증
     * @param requestObject
     * @param answerElements
     */
    public void validateSelectedElementIncludedAtElementList(SurveySubmitDTO.SurveySubmitObject requestObject, List<String> answerElements) {

        SurveyObject surveyObjectId = surveyService.findSurveyObject(requestObject.id());

        List<ElementObject> elementList = elementObjectRepository.findAllBySurveyObjectId(surveyObjectId.getId());

        Set<String> elementObjectIds = elementList.stream()
                .map(eo -> String.valueOf(eo.getId()))
                .collect(Collectors.toSet());

        Set<String> answerElementSet = Set.copyOf(answerElements);

        if (!elementObjectIds.containsAll(answerElementSet)) {
            throw new IllegalArgumentException("해당 요소에 포함되는 값을 제출해야 합니다.");
        }
    }
}