package com.innercircle.project_one.survey.api.service;

import com.innercircle.project_one.survey.api.dto.SurveySubmitDTO;
import com.innercircle.project_one.survey.api.repository.*;
import com.innercircle.project_one.survey.api.dto.SurveyDTO;
import com.innercircle.project_one.survey.api.dto.SurveyObjectDTO;
import com.innercircle.project_one.survey.common.SurveyObjectDataType;
import com.innercircle.project_one.survey.domain.*;
import com.innercircle.project_one.survey.domain.embeddable.SurveyObjectContent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyVersionRepository surveyVersionRepository;
    private final SurveyObjectRepository surveyObjectRepository;
    private final ElementObjectRepository elementObjectRepository;

    protected Survey findSurvey(Long surveyId) {
        return surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("설문조사를 찾을 수 없습니다."));
    }

    protected SurveyObject findSurveyObject(Long surveyId) {
        return surveyObjectRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("설문조사를 찾을 수 없습니다."));
    }

    protected List<SurveyObject> getSurveyVersionObjects(Long surveyId, SurveySubmitDTO surveySubmitDTO) {
        return surveyObjectRepository.findBySurveyIdAndSurveyVersionOrderByElementOrder(surveyId, surveySubmitDTO.version())
                .orElseThrow(() -> new IllegalArgumentException("설문조사를 찾을 수 없습니다."));
    }


    protected List<ElementObject> getElementObject(SurveyObjectDTO objectDTO, SurveyObject surveyObject) {
        List<ElementObject> elements = new ArrayList<>();
        int elementStringSize = objectDTO.elements().size();

        for (int i = 0; i < elementStringSize; i++) {
            String elementValue = objectDTO.elements().get(i);
            ElementObject elementObject = new ElementObject(i, elementValue, surveyObject);
            elements.add(elementObject);
          }

        return elements;
    }


    protected void saveSurveyObjects(Survey savedSurvey, SurveyDTO surveyDTO, SurveyVersion surveyVersion) {

        SurveyVersion savedSurveyVersion = surveyVersionRepository.save(surveyVersion);
        savedSurvey.updateSurveyVersion(savedSurveyVersion);
        surveyRepository.save(savedSurvey);

        List<SurveyObject> surveyObjects = new ArrayList<>();
        List<SurveyObjectDTO> objects = surveyDTO.objects();
        for (int i = 0; i < objects.size(); i++) {
            SurveyObject surveyObject = createSurveyObject(i, objects.get(i), savedSurvey, savedSurveyVersion);
            surveyObjects.add(surveyObject);
        }

        surveyObjectRepository.saveAll(surveyObjects);
    }


    protected SurveyObject createSurveyObject(int idx, SurveyObjectDTO objectDTO, Survey survey, SurveyVersion savedSurveyVersion) {

        SurveyObjectContent content   = new SurveyObjectContent(objectDTO.title(), objectDTO.description(), objectDTO.isRequired());
        SurveyObjectDataType dataType = SurveyObjectDataType.of(objectDTO.type().toUpperCase());

        SurveyObject surveyObject = SurveyObject.builder()
                .elementOrder(idx)
                .type(dataType)
                .surveyObjectContent(content)
                .survey(survey)
                .surveyVersion(savedSurveyVersion.getVersion())
                .build();

        if(dataType.isElementDataType()) {

            if (objectDTO.elements().isEmpty()) {
                throw new IllegalArgumentException("선택 리스트 요소는 1개 이상 지정되어야 합니다.");
            }

            List<ElementObject> elements = getElementObject(objectDTO, surveyObject);
            elementObjectRepository.saveAll(elements);
        }

        return surveyObject;
    }

}
