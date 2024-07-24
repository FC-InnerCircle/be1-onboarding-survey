package com.psh10066.survey.survey_management.adapter.out.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psh10066.survey.survey_management.domain.SurveyQuestion;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class SurveyQuestionsConverter implements AttributeConverter<List<SurveyQuestion>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<SurveyQuestion> surveyQuestions) {
        try {
            return objectMapper.writeValueAsString(surveyQuestions);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not convert survey questions to JSON");
        }
    }

    @Override
    public List<SurveyQuestion> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not convert JSON to survey questions");
        }
    }
}
