package com.psh10066.survey.survey_answer.adapter.out.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.psh10066.survey.survey_answer.domain.SurveyQuestionAnswer;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class SurveyQuestionAnswersConverter implements AttributeConverter<List<SurveyQuestionAnswer>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<SurveyQuestionAnswer> surveyQuestionAnswers) {
        try {
            return objectMapper.writeValueAsString(surveyQuestionAnswers);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not convert survey question answers to JSON");
        }
    }

    @Override
    public List<SurveyQuestionAnswer> convertToEntityAttribute(String s) {
        try {
            return objectMapper.readValue(s, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not convert JSON to survey question answers");
        }
    }
}
