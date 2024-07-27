package com.example.survey.usecase;

import com.example.survey.adapter.dto.SurveyDto;
import com.example.survey.adapter.dto.SurveyItemDto;
import com.example.survey.adapter.persistence.SurveyPersistence;
import com.example.survey.adapter.persistence.SurveyVersionPersistence;
import com.example.survey.domain.mapper.SurveyMapper;
import com.example.survey.domain.model.InputType;
import com.example.survey.domain.model.Survey;
import com.example.survey.domain.model.SurveyVersion;
import com.example.survey.exception.SurveyNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SurveyServiceTest {

    @Mock
    private SurveyPersistence surveyPersistence;

    @Mock
    private SurveyVersionPersistence surveyVersionPersistence;

    @InjectMocks
    private SurveyService surveyService;

    private SurveyDto validSurveyDto;
    private SurveyDto updatedSurveyDto;
    private Survey existingSurvey;

    @BeforeEach
    void setUp() {
        SurveyItemDto item1 = SurveyItemDto.builder()
                .name("Question 1")
                .description("Description 1")
                .inputType(InputType.SHORT_ANSWER)
                .required(true)
                .build();

        SurveyItemDto item2 = SurveyItemDto.builder()
                .name("Question 2")
                .description("Description 2")
                .inputType(InputType.LONG_ANSWER)
                .required(false)
                .build();

        validSurveyDto = SurveyDto.builder()
                .name("Test Survey")
                .description("Test Description")
                .items(Arrays.asList(item1, item2))
                .build();

        updatedSurveyDto = SurveyDto.builder()
            .name("Updated Survey")
            .description("Updated Description")
            .items(Arrays.asList(item1, item2))
            .build();

        existingSurvey = Survey.builder()
            .id(1L)
            .name("Test Survey")
            .description("Test Description")
            .surveyItems(SurveyMapper.surveyItemDtosToSurveyItems(Arrays.asList(item1, item2)))
            .currentVersion(1)
            .build();
    }

    @Test
    @DisplayName("Create survey with valid data")
    void testCreateSurveyWithValidData() {
        Survey survey = SurveyMapper.surveyDtoToSurvey(validSurveyDto);
        survey.setId(1L);
        survey.setCurrentVersion(1);

        when(surveyPersistence.save(any(Survey.class))).thenReturn(survey);

        SurveyDto createdSurveyDto = surveyService.createSurvey(validSurveyDto);

        assertEquals(validSurveyDto.getName(), createdSurveyDto.getName());
        assertEquals(validSurveyDto.getDescription(), createdSurveyDto.getDescription());
        assertEquals(validSurveyDto.getItems().size(), createdSurveyDto.getItems().size());

        ArgumentCaptor<SurveyVersion> versionCaptor = ArgumentCaptor.forClass(SurveyVersion.class);
        verify(surveyVersionPersistence).save(versionCaptor.capture());

        SurveyVersion capturedVersion = versionCaptor.getValue();
        assertEquals(1, capturedVersion.getVersion());
        assertEquals(survey, capturedVersion.getSurvey());
    }

    @Test
    @DisplayName("Update survey with valid data")
    void testUpdateSurveyWithValidData() {
        when(surveyPersistence.findById(anyLong())).thenReturn(Optional.of(existingSurvey));
        when(surveyPersistence.save(any(Survey.class))).thenReturn(existingSurvey);

        SurveyDto updatedSurvey = surveyService.updateSurvey(1L, updatedSurveyDto);

        assertEquals(updatedSurveyDto.getName(), updatedSurvey.getName());
        assertEquals(updatedSurveyDto.getDescription(), updatedSurvey.getDescription());

        ArgumentCaptor<Survey> surveyCaptor = ArgumentCaptor.forClass(Survey.class);
        verify(surveyPersistence).save(surveyCaptor.capture());

        Survey capturedSurvey = surveyCaptor.getValue();
        assertEquals(updatedSurveyDto.getName(), capturedSurvey.getName());
        assertEquals(updatedSurveyDto.getDescription(), capturedSurvey.getDescription());
    }

    @Test
    @DisplayName("Update non-existing survey")
    void testUpdateNonExistingSurvey() {
        when(surveyPersistence.findById(anyLong())).thenReturn(Optional.empty());

        SurveyNotFoundException exception = assertThrows(
            SurveyNotFoundException.class,
            () -> surveyService.updateSurvey(1L, updatedSurveyDto)
        );

        assertEquals("Survey not found with id: 1", exception.getMessage());
        verify(surveyPersistence, never()).save(any(Survey.class));
    }
}
