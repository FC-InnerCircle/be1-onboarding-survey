package lshh.be1onboardingsurvey.survey.domain;

import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand;
import lshh.be1onboardingsurvey.survey.domain.command.CreateSurveyCommand;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SurveyServiceMiddleTest {
    @Autowired
    private SurveyService surveyService;

    @Nested
    class CreateItemTest{
        @Test
        @DisplayName("간단한 survey 생성")
        public void testCreate_Simple() {
            // Arrange
            CreateSurveyCommand command = new CreateSurveyCommand("testCreate_Simple", "description");

            // Act
            Result commandResult = surveyService.create(command);

            // Assert
            assertEquals(Result.Status.SUCCESS, commandResult.status());
            List<SurveyView> result = surveyService.findAll();
            assertTrue(result.stream().anyMatch(s -> s.name().equals("testCreate_Simple")));
            Survey survey = surveyService.findByName("testCreate_Simple");
            assertEquals("testCreate_Simple", survey.getName());
        }
    }

    @Nested
    class AddItemTest{
        @Test
        @DisplayName("간단한 item 추가")
        public void testAddItem_Simple() {
            // Arrange
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("testAddItem_Simple", "description");
            Result createSurveyCommandResult = surveyService.create(createSurveyCommand);
            assertEquals(Result.Status.SUCCESS, createSurveyCommandResult.status());
            Survey survey = surveyService.findByName("testAddItem_Simple");
            Long surveyId = survey.getId();

            AddSurveyItemCommand command = new AddSurveyItemCommand(surveyId, "testAddItem_Simple", "description", SurveyItemForm.TEXT, true);

            // Act
            Result result = surveyService.addItem(command);

            // Assert
            assertEquals(Result.success(), result);
        }
    }
}
