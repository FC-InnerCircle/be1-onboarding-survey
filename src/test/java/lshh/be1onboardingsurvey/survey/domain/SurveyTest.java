package lshh.be1onboardingsurvey.survey.domain;

import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand;
import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemOptionCommand;
import lshh.be1onboardingsurvey.survey.domain.command.UpdateSurveyItemCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class SurveyTest {

    @Nested
    @DisplayName("항목 추가")
    public class AddItemTest{
        @Test
        public void testAddItem() {
            // Initialize Survey with empty item list
            Survey sut = Survey.builder()
                    .name("Test Survey")
                    .description("This is a test survey")
                    .items(new ArrayList<>())
                    .build();

            // Create mock AddSurveyItemCommand
            AddSurveyItemCommand command = new AddSurveyItemCommand(
                    1L,
                    "Test Item",
                    "This is a test item",
                    SurveyItemForm.TEXT,
                    true,
                    1L
            );

            // Add the new item
            sut.addItem(command);

            // Assert that the item has been added
            assertEquals(1, sut.getItems().size());
            assertEquals(sut, sut.getItems().getFirst().survey);
        }
    }

    @Nested
    @DisplayName("항목 옵션 추가")
    public class AddSurveyItemOptionTest{
        @Test
        public void testAddSurveyItemOption() {
            // Initialize Survey with single item list
            SurveyItem initialItem = SurveyItem.builder()
                    .id(1L)
                    .form(SurveyItemForm.RADIO)
                    .build();
            List<SurveyItem> itemList = new ArrayList<>();
            itemList.add(initialItem);
            Survey sut = Survey.builder()
                .name("Test Survey")
                .description("This is a test survey")
                .items(itemList)
                .build();

            // Create mock AddSurveyItemOptionCommand
            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(
                    1L,
                    1L,
                    "Test Option",
                    "This is a test option",
                    1L
            );

            // Update the item with new option
            sut.updateItem(command);

            // Assert that the item has been updated with new option
            assertEquals(1, sut.findItem(1L).orElseThrow().getOptions().size());
            assertEquals("Test Option", sut.findItem(1L).orElseThrow().getOptions().getFirst().getName());
        }
    }

    @Nested
    @DisplayName("항목 수정")
    public class UpdateItemTest{
        @Test
        public void testUpdateItemCommand() {
            // Initialize Survey with single item list
            SurveyItem initialItem = SurveyItem.builder()
                    .id(1L)
                    .name("Latest Item")
                    .description("This is the latest item")
                    .form(SurveyItemForm.TEXT)
                    .required(true)
                    .sequence(1L)
                    .build();
            List<SurveyItem> itemList = new ArrayList<>();
            itemList.add(initialItem);
            Survey sut = Survey.builder()
                .name("Test Survey")
                .description("This is a test survey")
                .items(itemList)
                .build();

            // Create mock UpdateSurveyItemCommand
            UpdateSurveyItemCommand command = new UpdateSurveyItemCommand(
                    1L,
                    1L,
                    "Updated Item",
                    "This is an updated item",
                    SurveyItemForm.TEXT,
                    true,
                    1L
            );

            // Update the item with new information
            sut.updateItem(command);

            // Assert that the item has been updated
            SurveyItem latest = sut.findItem(1L).orElseThrow();
            assertEquals("Latest Item", latest.getName());

            SurveyItem updated = sut.findItemBySequence(1L).orElseThrow();
            assertEquals("Updated Item", updated.getName());
        }
    }

}
