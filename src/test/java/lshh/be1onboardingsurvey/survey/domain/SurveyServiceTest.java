package lshh.be1onboardingsurvey.survey.domain;

import lshh.be1onboardingsurvey.survey.domain.command.CreateSurveyCommand;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {

    @Mock
    private SurveyRepository repository;

    @InjectMocks
    private SurveyService service;

    @Nested
    class CreateTest{
        @Test
        @DisplayName("간단한 survey 생성과 저장")
        public void testCreate_Simple() {
            // Arrange
            CreateSurveyCommand command = new CreateSurveyCommand("name", "description");
            Survey expectedSurvey = Survey.of(command);
            Result expectedResult = Result.success();
    
            when(repository.save(any(Survey.class))).thenReturn(expectedResult);
    
            // Act
            Result result = service.create(command);
    
            // Assert
            assertEquals(expectedResult, result);
        }
        
        
    }
}