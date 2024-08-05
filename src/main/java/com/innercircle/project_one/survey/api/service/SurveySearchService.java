package com.innercircle.project_one.survey.api.service;

import com.innercircle.project_one.survey.api.dto.*;
import com.innercircle.project_one.survey.api.repository.*;
import com.innercircle.project_one.survey.common.ApiResponse;
import com.innercircle.project_one.survey.common.SuccessResponse;
import com.innercircle.project_one.survey.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SurveySearchService {

    private final SurveyRepository surveyRepository;
    private final SurveyObjectRepository surveyObjectRepository;
    private final ElementObjectRepository elementObjectRepository;
    private final SurveyAnswerRepository surveyAnswerRepository;
    private final SurveyObjectAnswerRepository surveyObjectAnswerRepository;
    private final ElementSurveyObjectAnswerRepository elementSurveyObjectAnswerRepository;
    private final StringSurveyObjectAnswerRepository stringSurveyObjectAnswerRepository;

    @Transactional(readOnly = true)
    public ApiResponse getSurveyList(Long surveyId, int pageNum) {

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 설문을 찾을 수 없습니다: (" + surveyId + ")"));
        // 설문 목록 조회
        List<SurveyAnswer> surveyAnswerList = surveyAnswerRepository.findBySurveyId(survey.getId());
        List<SurveyObject> surveyObjectList = surveyObjectRepository.findBySurveyId(surveyId);

        // 설문 요소 조회
        List<ElementObject> surveyElementObjectList = surveyObjectList.stream()
                .flatMap(surveyObject -> elementObjectRepository.findAllBySurveyObjectId(surveyObject.getId()).stream()).toList();

        // 설문 요소 내 선택값 조회
        List<StringSurveyObjectAnswer> stringSurveyObjectAnswerList = surveyObjectList.stream()
                .flatMap(surveyObject -> stringSurveyObjectAnswerRepository
                        .findAllBySurveyObjectId(surveyObject.getId()).stream()).toList();

        List<ElementSurveyObjectAnswer> elementSurveyObjectAnswerList = surveyObjectList.stream()
                .flatMap(surveyObject -> elementSurveyObjectAnswerRepository
                        .findAllBySurveyObjectId(surveyObject.getId()).stream()).toList();

        // 리스트 매핑
        List<SurveyAnswerListDTO> surveyAnswerDTOList = surveyAnswerList.stream().map(surveyAnswer -> {

            List<SurveyObject> filteredSurveyObjectList = surveyObjectList.stream()
                    .filter(surveyObject -> surveyObject.getSurveyVersion().equals(surveyAnswer.getSurveyVersionId()))
                    .sorted(Comparator.comparingInt(SurveyObject::getElementOrder)).toList();

            List<SurveyListObjectDTO> surveyObjectDTOList = filteredSurveyObjectList.stream()
                    .map(surveyObject -> {
                        String type = surveyObject.getType().name().toLowerCase();
                        String id = surveyObject.getId().toString();
                        Object content = null;

                        switch (type) {
                            case "text", "rich_text" -> {
                                StringSurveyObjectAnswer stringAnswer = stringSurveyObjectAnswerList.stream()
                                        .filter(answer -> answer.getSurveyObject().getId().equals(surveyObject.getId()))
                                        .findFirst().orElse(null);
                                if (stringAnswer != null) {
                                    content = stringAnswer.getAnswer();
                                }
                            }
                            case "radio", "check_box" -> content = surveyElementObjectList.stream()
                                    .filter(element -> element.getSurveyObject().getId().equals(surveyObject.getId()))
                                    .map(element -> {
                                        boolean selected = elementSurveyObjectAnswerList.stream()
                                                .anyMatch(answer -> answer.getElementObject().getId().equals(element.getId()));
                                        return new ElementDTO(element.getId().toString(), element.getElementValue(), selected);
                                    })
                                    .collect(Collectors.toList());
                        }

                        return new SurveyListObjectDTO(type, id, content);
                    })
                    .collect(Collectors.toList());

            return new SurveyAnswerListDTO(
                    surveyAnswer.getId().toString(),
                    survey.getTitle(),
                    survey.getDescription(),
                    surveyObjectDTOList
            );
        }).collect(Collectors.toList());

        return new SuccessResponse<>("설문조회 응답 조회 완료", surveyAnswerDTOList);
    }
}



