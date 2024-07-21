package lshh.be1onboardingsurvey.survey.domain;

import lshh.be1onboardingsurvey.survey.domain.command.*;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyResponseItemView;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyResponseView;
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
            Result<?> commandResult = surveyService.create(command);

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
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("testAddItem_Simple", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("testAddItem_Simple").orElseThrow();
            Long surveyId = surveyView.id();

            // Act
            AddSurveyItemCommand command = new AddSurveyItemCommand(surveyId, "testAddItem_Simple", "description", SurveyItemFormType.TEXT, true, 1L);
            Result<?> result = surveyService.addItem(command);

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
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("survey_testUpdateItem_Simple", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("survey_testUpdateItem_Simple").orElseThrow();
            Long surveyId = surveyView.id();
            System.out.println(surveyView);
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "item_testUpdateItem_Simple", "description", SurveyItemFormType.RADIO, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyViewWithItem = surveyService.findByName("survey_testUpdateItem_Simple").orElseThrow();
            Long itemId = surveyViewWithItem.items().getFirst().id();
            System.out.println(surveyViewWithItem);
            // survey item option 생성
            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(surveyId, itemId, "option_testUpdateItem_Simple", "description", 1L);
            surveyService.addItemOption(command);

            // Act
            UpdateSurveyItemCommand updateSurveyItemCommand = new UpdateSurveyItemCommand(
                    surveyId,
                    itemId,
                    "item_updated_testUpdateItem_Simple",
                    "description",
                    SurveyItemFormType.RADIO,
                    true,
                    2L
            );
            Result<?> result = surveyService.updateItem(updateSurveyItemCommand);

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
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("testAddItemOption_Simple", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("testAddItemOption_Simple").orElseThrow();
            Long surveyId = surveyView.id();
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "testAddItemOption_Simple", "description", SurveyItemFormType.RADIO, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyViewWithItem = surveyService.findByName("testAddItemOption_Simple").orElseThrow();
            Long itemId = surveyViewWithItem.items().getFirst().id();

            // Act
            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(surveyId, itemId, "testAddItemOption_Simple", "description", 1L);
            Result<?> result = surveyService.addItemOption(command);

            // Assert
            assertEquals(Result.success(), result);
            SurveyView surveyWithItemOption = surveyService.findByName("testAddItemOption_Simple").orElseThrow();
            assertEquals(1, surveyWithItemOption.items().getFirst().options().size());
        }
    }
    @Nested
    @DisplayName("Survey response 추가")
    class AddResponseTest{
        @Test
        @DisplayName("간단한 response 추가")
        public void testAddResponse_Simple() {
            // Arrange
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("testAddResponse_Simple", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("testAddResponse_Simple").orElseThrow();
            Long surveyId = surveyView.id();
            AddSurveyResponseCommand command = new AddSurveyResponseCommand(surveyId);

            // Act
            Result<?> result = surveyService.addResponse(command);

            // Assert
            assertEquals(Result.success(), result);
        }
    }

    @Nested
    @DisplayName("Survey response item 추가")
    class AddResponseItemTest {
        @Test
        @DisplayName("간단한 response item 추가")
        public void testAddResponseItem_Simple() {
            // Arrange
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("testAddResponseItem_Simple", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("testAddResponseItem_Simple").orElseThrow();
            Long surveyId = surveyView.id();
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "testAddResponseItem_Simple", "description", SurveyItemFormType.TEXT, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyView2 = surveyService.findByName("testAddResponseItem_Simple").orElseThrow();
            Long surveyItemId = surveyView2.items().getFirst().id();
            // survey response 생성
            AddSurveyResponseCommand addSurveyResponseCommand = new AddSurveyResponseCommand(surveyId);
            surveyService.addResponse(addSurveyResponseCommand);
            SurveyResponseView responseView = surveyService.findResponse(surveyId).getFirst();

            // Act
            // response item 추가
            AddSurveyResponseItemCommand command = new AddSurveyResponseItemCommand(
                    surveyId,
                    responseView.id(),
                    surveyItemId,
                    "item_testAddResponseItem_Simple"
            );
            Result<?> result = surveyService.addResponseItem(command);

            // Assert
            assertEquals(Result.success(), result);
            SurveyResponseView responseView2 = surveyService.findResponse(surveyId).getFirst();
            SurveyResponseItemView itemView = responseView2.items().getFirst();
            assertEquals("item_testAddResponseItem_Simple", itemView.value());

        }
    }
}
