package lshh.be1onboardingsurvey.survey.domain;

import lshh.be1onboardingsurvey.common.lib.clock.Clock;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemOptionCommand;
import lshh.be1onboardingsurvey.survey.domain.command.CreateSurveyCommand;
import lshh.be1onboardingsurvey.survey.domain.command.UpdateSurveyItemCommand;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class SurveyServiceTest {
    @Autowired
    private SurveyService surveyService;

    @Nested
    @DisplayName("Survey 생성")
    class CreateSurveyTest{
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
            SurveyView surveyView = surveyService.findByName("testCreate_Simple").orElseThrow();
            assertEquals("testCreate_Simple", surveyView.name());
        }
    }

    @Nested
    @DisplayName("Survey 항목 추가")
    class AddItemTest{
        @Test
        @DisplayName("간단한 item 추가")
        public void testAddItem_Simple() {
            // Arrange
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("testAddItem_Simple", "description");
            Result createSurveyCommandResult = surveyService.create(createSurveyCommand);
            assertEquals(Result.Status.SUCCESS, createSurveyCommandResult.status());
            SurveyView surveyView = surveyService.findByName("testAddItem_Simple").orElseThrow();
            Long surveyId = surveyView.id();

            AddSurveyItemCommand command = new AddSurveyItemCommand(surveyId, "testAddItem_Simple", "description", SurveyItemForm.TEXT, true, 1L);

            // Act
            Result result = surveyService.addItem(command);

            // Assert
            assertEquals(Result.success(), result);
            SurveyView surveyWithItem = surveyService.findByName("testAddItem_Simple").orElseThrow();
            System.out.println(surveyWithItem.items());
            assertEquals(1, surveyWithItem.items().size());
        }
    }

    @Nested
    @DisplayName("Survey 항목 수정")
    class UpdateItemTest{
        @Test
        @DisplayName("간단한 item 수정")
        public void testUpdateItem_Simple() {
            // Arrange
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("survey_testUpdateItem_Simple", "description");
            Result createSurveyCommandResult = surveyService.create(createSurveyCommand);
            assertEquals(Result.Status.SUCCESS, createSurveyCommandResult.status());
            SurveyView surveyView = surveyService.findByName("survey_testUpdateItem_Simple").orElseThrow();
            Long surveyId = surveyView.id();
            System.out.println(surveyView);

            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "item_testUpdateItem_Simple", "description", SurveyItemForm.RADIO, true, 1L);
            Result addSurveyItemResult = surveyService.addItem(addSurveyItemCommand);
            assertEquals(Result.Status.SUCCESS, addSurveyItemResult.status());
            SurveyView surveyViewWithItem = surveyService.findByName("survey_testUpdateItem_Simple").orElseThrow();
            Long itemId = surveyViewWithItem.items().getFirst().id();
            System.out.println(surveyViewWithItem);

            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(surveyId, itemId, "option_testUpdateItem_Simple", "description", 1L);
            Result addSurveyItemOptionResult = surveyService.addItemOption(command);
            assertEquals(Result.Status.SUCCESS, addSurveyItemOptionResult.status());

            // Act
            UpdateSurveyItemCommand updateSurveyItemCommand = new UpdateSurveyItemCommand(
                    surveyId,
                    itemId,
                    "item_updated_testUpdateItem_Simple",
                    "description",
                    SurveyItemForm.RADIO,
                    true,
                    2L
            );
            Result result = surveyService.updateItem(updateSurveyItemCommand);

            // Assert
            assertEquals(Result.success(), result);
            SurveyView updatedSurvey = surveyService.findByName("survey_testUpdateItem_Simple").orElseThrow();
            System.out.println(updatedSurvey);
            assertEquals("item_updated_testUpdateItem_Simple", updatedSurvey.items().getFirst().name());
        }
    }

    @Nested
    @DisplayName("Survey 항목 옵션 추가")
    class AddItemOptionTest{
        @Test
        @DisplayName("간단한 item option 추가")
        public void testAddItemOption_Simple() {
            // Arrange
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("testAddItemOption_Simple", "description");
            Result createSurveyCommandResult = surveyService.create(createSurveyCommand);
            assertEquals(Result.Status.SUCCESS, createSurveyCommandResult.status());
            SurveyView surveyView = surveyService.findByName("testAddItemOption_Simple").orElseThrow();
            Long surveyId = surveyView.id();

            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "testAddItemOption_Simple", "description", SurveyItemForm.RADIO, true, 1L);
            Result addSurveyItemResult = surveyService.addItem(addSurveyItemCommand);
            assertEquals(Result.Status.SUCCESS, addSurveyItemResult.status());
            SurveyView surveyViewWithItem = surveyService.findByName("testAddItemOption_Simple").orElseThrow();
            Long itemId = surveyViewWithItem.items().getFirst().id();

            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(surveyId, itemId, "testAddItemOption_Simple", "description", 1L);

            // Act
            Result result = surveyService.addItemOption(command);

            // Assert
            assertEquals(Result.success(), result);
            SurveyView surveyWithItemOption = surveyService.findByName("testAddItemOption_Simple").orElseThrow();
            assertEquals(1, surveyWithItemOption.items().getFirst().options().size());
        }
    }
}
