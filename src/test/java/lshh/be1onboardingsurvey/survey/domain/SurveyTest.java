package lshh.be1onboardingsurvey.survey.domain;

import lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class SurveyTest {

    @Test
    public void testAddItem() {
        // Initialize Survey with empty item list
        Survey mockSurvey = Survey.builder()
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
                true
        );
        
        // Add the new item
        mockSurvey.addItem(command);

        // Assert that the item has been added
        assertEquals(1, mockSurvey.getItems().size());
        assertEquals(mockSurvey, mockSurvey.getItems().getFirst().survey);
    }
}