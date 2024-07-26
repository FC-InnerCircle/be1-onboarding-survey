package com.innercircle.project_one.survey.api;

import com.innercircle.project_one.survey.api.repository.ElementObjectRepository;
import com.innercircle.project_one.survey.api.repository.SurveyObjectRepository;
import com.innercircle.project_one.survey.api.repository.SurveyRepository;
import com.innercircle.project_one.survey.api.repository.SurveyVersionRepository;
import com.innercircle.project_one.survey.api.dto.SurveyDTO;
import com.innercircle.project_one.survey.api.dto.SurveyObjectDTO;
import com.innercircle.project_one.survey.common.ApiResponse;
import com.innercircle.project_one.survey.common.SuccessResponse;
import com.innercircle.project_one.survey.common.SurveyObjectDataType;
import com.innercircle.project_one.survey.domain.*;
import com.innercircle.project_one.survey.domain.embeddable.SurveyObjectContent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final SurveyVersionRepository surveyVersionRepository;
    private final SurveyObjectRepository surveyObjectRepository;
    private final ElementObjectRepository elementObjectRepository;

    public SurveyService(
            SurveyRepository surveyRepository,
            SurveyVersionRepository surveyVersionRepository,
            SurveyObjectRepository surveyObjectRepository,
            ElementObjectRepository elementObjectRepository) {
        this.surveyRepository = surveyRepository;
        this.surveyVersionRepository = surveyVersionRepository;
        this.surveyObjectRepository = surveyObjectRepository;
        this.elementObjectRepository = elementObjectRepository;
    }

    @Transactional
    public ApiResponse saveSurvey(SurveyDTO surveyDTO) {
        Survey survey = createSurvey(surveyDTO);
        SurveyVersion newSurveyVersion = createSurveyVersion(survey);
        saveSurveyObjects(survey, surveyDTO, newSurveyVersion);
        return new SuccessResponse<>("설문조사 폼이 저장되었습니다.");
    }

    @Transactional
    public ApiResponse updateSurvey(Long surveyId, SurveyDTO surveyDTO) {
        Survey findSurvey = findSurvey(surveyId);
        findSurvey.updateSurveyTitleAndDescription(surveyDTO);
        Survey savedSurvey = surveyRepository.save(findSurvey);

        SurveyVersion latestVersion = surveyVersionRepository.findTopBySurveyOrderByVersionDesc(savedSurvey);
        latestVersion.setVersion(latestVersion.getVersion() + 1);

        saveSurveyObjects(savedSurvey, surveyDTO, latestVersion);
        return new SuccessResponse<>("설문조사 폼이 업데이트되었습니다.");
    }

    private void saveSurveyObjects(Survey savedSurvey, SurveyDTO surveyDTO, SurveyVersion surveyVersion) {

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

    private SurveyObject createSurveyObject(int idx, SurveyObjectDTO objectDTO, Survey survey, SurveyVersion savedSurveyVersion) {

        SurveyObjectContent content   = new SurveyObjectContent(objectDTO);
        SurveyObjectDataType dataType = SurveyObjectDataType.of(objectDTO.type().toUpperCase());

        SurveyObject surveyObject = SurveyObject.builder()
                .elementOrder(idx)
                .type(dataType)
                .surveyObjectContent(content)
                .survey(survey)
                .surveyVersionId(savedSurveyVersion.getVersion())
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


    private Survey createSurvey(SurveyDTO surveyDTO) {
        return surveyRepository.save(new Survey(surveyDTO.title(), surveyDTO.description()));
    }
    private Survey findSurvey(Long surveyId) {
        return surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("설문조사를 찾을 수 없습니다."));
    }

    private SurveyVersion createSurveyVersion(Survey survey) {
        return surveyVersionRepository.save(new SurveyVersion(1L, survey));
    }

    private List<ElementObject> getElementObject(SurveyObjectDTO objectDTO, SurveyObject surveyObject) {
        List<ElementObject> elements = new ArrayList<>();
        int elementStringSize = objectDTO.elements().size();

        for (int i = 0; i < elementStringSize; i++) {
            String elementValue = objectDTO.elements().get(i);
            ElementObject elementObject = new ElementObject(i, elementValue, surveyObject);
            elements.add(elementObject);
          }

        return elements;
    }

}
