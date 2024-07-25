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

        Survey survey = new Survey(surveyDTO.title(), surveyDTO.description());

        Survey savedSurvey = surveyRepository.save(survey);

        SurveyVersion latestVersion = surveyVersionRepository.findTopBySurveyOrderByVersionDesc(savedSurvey);
        int newVersionNumber = (latestVersion == null) ? 1 : latestVersion.getVersion() + 1;

        SurveyVersion newSurveyVersion = new SurveyVersion(newVersionNumber, savedSurvey);
        SurveyVersion savedSurveyVersion = surveyVersionRepository.save(newSurveyVersion);

        savedSurvey.updateSurveyVersion(savedSurveyVersion);
        surveyRepository.save(savedSurvey);

        List<SurveyObject> surveyObjects = new ArrayList<>();
        List<SurveyObjectDTO> objects = surveyDTO.objects();
        for (int i = 0; i < objects.size(); i++) {
            SurveyObject surveyObject = createSurveyObject(i, objects.get(i), savedSurvey, savedSurveyVersion);
            surveyObjects.add(surveyObject);
        }

        surveyObjectRepository.saveAll(surveyObjects);

        return new SuccessResponse<>("설문조사 폼이 저장되었습니다.");
    }

    private SurveyObject createSurveyObject(int idx, SurveyObjectDTO objectDTO, Survey survey, SurveyVersion savedSurveyVersion) {

        SurveyObjectContent content = new SurveyObjectContent(
                objectDTO.title(),
                objectDTO.description(),
                objectDTO.isRequired()
        );

        SurveyObjectDataType dataType = SurveyObjectDataType.of(objectDTO.type().toUpperCase());

        SurveyObject surveyObject = SurveyObject.builder()
                                        .elementOrder(idx)
                                        .type(dataType)
                                        .surveyObjectContent(content)
                                        .survey(survey)
                                        .surveyVersionId(savedSurveyVersion.getVersion())
                                        .build();

        if(dataType.isElementDataType()) {
            List<ElementObject> elements = new ArrayList<>();
            int elementStringSize = objectDTO.elements().size();

            if (objectDTO.elements().isEmpty()) {
                throw new IllegalArgumentException("선택 리스트 요소는 1개 이상 지정되어야 합니다.");
            }

            for (int i = 0; i < elementStringSize; i++) {
                String elementValue = objectDTO.elements().get(i);
                ElementObject elementObject = new ElementObject(i, elementValue, surveyObject);
                elements.add(elementObject);
            }

            elementObjectRepository.saveAll(elements);
        }

        return surveyObject;
    }
}