package com.innercircle.project_one.survey.api.service;

import com.innercircle.project_one.survey.api.dto.SurveySubmitDTO;
import com.innercircle.project_one.survey.api.repository.*;
import com.innercircle.project_one.survey.common.ApiResponse;
import com.innercircle.project_one.survey.common.SuccessResponse;
import com.innercircle.project_one.survey.common.SurveyObjectDataType;
import com.innercircle.project_one.survey.domain.*;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SurveyAnswerService {

    private final SurveyService surveyService;
    private final SurveyObjectAnswerRepository surveyObjectAnswerRepository;
    private final EntityManager entityManager;
    private final SurveyValidator surveyValidator;

    @Transactional
    public ApiResponse submitSurveyResponse(Long surveyId, SurveySubmitDTO surveySubmitDTO) {

        Survey survey = surveyService.findSurvey(surveyId);
        surveyValidator.validateSubmitable(survey, surveySubmitDTO);

        List<SurveyObject> surveyObjects = survey.getSurveyObjects();
        survey.sortSurveyObjects();

        List<SurveySubmitDTO.SurveySubmitObject> objects = surveySubmitDTO.objects();

        for (int i = 0; i < objects.size(); i++) {
            SurveySubmitDTO.SurveySubmitObject submitObject = objects.get(i);

            List<SurveyObjectAnswer> answers = createAnswer(surveyObjects.get(i), submitObject);
            for (SurveyObjectAnswer answer : answers) {
                surveyObjectAnswerRepository.save(answer);
            }
        }

        return new SuccessResponse<>("설문조사 응답이 제출되었습니다.");
    }


    private List<SurveyObjectAnswer> createAnswer(SurveyObject surveyObject, SurveySubmitDTO.SurveySubmitObject requestObject) {
        List<SurveyObjectAnswer> answers = new ArrayList<>();
        switch (SurveyObjectDataType.of(requestObject.type())) {
            case TEXT, RICH_TEXT -> {
                String content = ((SurveySubmitDTO.SurveySubmitObject.StringContent) requestObject.content()).getContent();
                answers.add(StringSurveyObjectAnswer.builder()
                        .surveyObject(surveyObject)
                        .answer(content)
                        .build());
            }
            case RADIO -> {
                String selectedElement = ((SurveySubmitDTO.SurveySubmitObject.ElementContent) requestObject.content()).getSelectedElement();
                surveyValidator.validateSelectedElementIncludedAtElementList(requestObject, List.of(selectedElement));

                ElementObject elementObject = entityManager.getReference(ElementObject.class, Long.parseLong(selectedElement));
                answers.add(ElementSurveyObjectAnswer.builder()
                        .surveyObject(surveyObject)
                        .answer(selectedElement)
                        .elementObject(elementObject)
                        .build());
            }
            case CHECK_BOX -> {
                List<String> selectedElements = ((SurveySubmitDTO.SurveySubmitObject.CheckBoxContent) requestObject.content()).getSelectedElements();
                surveyValidator.validateSelectedElementIncludedAtElementList(requestObject, selectedElements);

                for (int i = 0; i < selectedElements.size(); i++) {
                    ElementObject elementObject = entityManager.getReference(ElementObject.class, i + 1);
                    answers.add(ElementSurveyObjectAnswer.builder()
                            .surveyObject(surveyObject)
                            .answer(selectedElements.get(i))
                            .elementObject(elementObject)
                            .build());
                }
            }
            default -> throw new IllegalArgumentException("적절한 입력 타입이 아닙니다.");
        }
        return answers;
    }

}
