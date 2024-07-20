package lshh.be1onboardingsurvey.survey.domain;

import lombok.RequiredArgsConstructor;
import lshh.be1onboardingsurvey.survey.domain.command.CreateSurveyCommand;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SurveyServiceMiddleTest {
    @Autowired
    private SurveyService surveyService;

    @Test
    public void testCreate_Simple() {
        // Arrange
        CreateSurveyCommand command = new CreateSurveyCommand("name", "description");
        Result expectedResult = Result.success();

        // Act
        Result result = surveyService.create(command);

        // Assert
        assertEquals(expectedResult, result);


    }
}
