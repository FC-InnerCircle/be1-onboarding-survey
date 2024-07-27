package lshh.be1onboardingsurvey.survey.domain;

import lshh.be1onboardingsurvey.common.lib.clock.Clock;
import lshh.be1onboardingsurvey.survey.domain.command.*;
import lshh.be1onboardingsurvey.survey.domain.entity.*;
import lshh.be1onboardingsurvey.survey.domain.vo.SurveyResponseItemValue;
import lshh.be1onboardingsurvey.survey.domain.vo.Version;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SurveyTest {

    private final Clock fakeClock = () -> LocalDateTime.of(2024, 7, 20, 11, 46, 0);

    @Nested
    @DisplayName("항목 추가")
    public class AddItemTest{
        @Test
        @DisplayName("항목 추가 성공")
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
                    SurveyItemFormType.TEXT,
                    true,
                    1L
            );

            // Add the new item
            sut.addItem(command, fakeClock.now());

            // Assert that the item has been added
            assertEquals(1, sut.getItems().size());
            assertEquals(sut, sut.getItems().getFirst().getSurvey());
        }
    }

    @Nested
    @DisplayName("항목 옵션 추가")
    public class AddSurveyItemOptionTest{
        @Test
        @DisplayName("항목 옵션 추가 성공")
        public void testAddSurveyItemOption() {
            // Initialize Survey with single item list
            SurveyItem initialItem = SurveyItem.builder()
                    .id(1L)
                    .formType(SurveyItemFormType.RADIO)
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
            sut.updateItem(command, fakeClock.now());

            // Assert that the item has been updated with new option
            assertEquals(1, sut.findItem(1L).orElseThrow().getOptions().size());
            assertEquals("Test Option", sut.findItem(1L).orElseThrow().getOptions().getFirst().getName());
        }
    }

    @Nested
    @DisplayName("항목 수정")
    public class UpdateItemTest{
        @Test
        @DisplayName("항목 수정 성공")
        public void testUpdateItemCommand() {
            // Initialize Survey with single item list
            SurveyItem initialItem = SurveyItem.builder()
                    .id(1L)
                    .name("Latest Item")
                    .description("This is the latest item")
                    .formType(SurveyItemFormType.TEXT)
                    .required(true)
                    .sequence(1L)
                    .version(Version.forCreate(fakeClock.now()))
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
                    SurveyItemFormType.TEXT,
                    true,
                    1L
            );

            // Update the item with new information
            sut.updateItem(command, fakeClock);

            // Assert that the item has been updated
            SurveyItem latest = sut.findItem(1L).orElseThrow();
            assertEquals("Latest Item", latest.getName());

            SurveyItem updated = sut.findItemBySequence(1L).orElseThrow();
            assertEquals("Updated Item", updated.getName());
        }
    }

    @Nested
    @DisplayName("항목 최신 조회")
    public class FindLatestItemTest{
        @Test
        @DisplayName("최신 항목 조회 성공")
        public void testFindLatestItem() {
            // Initialize Survey with two items

            SurveyItem initialItem1 = SurveyItem.builder()
                    .id(1L)
                    .formType(SurveyItemFormType.RADIO)
                    .sequence(1L)
                    .build();
            initialItem1.setVersion(Version.forOverwriten(null, fakeClock.now()));

            SurveyItem initialItem2 = SurveyItem.builder()
                    .id(2L)
                    .formType(SurveyItemFormType.RADIO)
                    .build();
            initialItem2.setVersion(Version.forUpdate(initialItem1, fakeClock.now()));

            List<SurveyItem> itemList = new ArrayList<>();
            itemList.add(initialItem1);
            itemList.add(initialItem2);

            Survey sut = Survey.builder()
                .name("Test Survey")
                .description("This is a test survey")
                .items(itemList)
                .build();
            System.out.println(sut.getItems());

            // Assert that the correct latest item is found
            Optional<SurveyItem> latestItem = sut.findLatestItem(1L);
            assertTrue(latestItem.isPresent());
            assertEquals(2L, latestItem.get().getId());
        }
    }
    @Nested
    @DisplayName("응답 추가")
    public class AddResponseTest{
        @Test
        @DisplayName("응답 추가 성공")
        public void testAddResponseSuccess() {
            // Initialize Survey with empty response list
            Survey sut = Survey.builder()
                    .name("Test Survey")
                    .description("This is a test survey")
                    .responses(new ArrayList<>())
                    .build();

            // Create mock AddSurveyResponseCommand
            SurveyResponse response = SurveyResponse.builder()
                    .id(1L)
                    .build();

            // Add the new response
            sut.addResponse(response);

            // Assert that the response has been added
            assertEquals(1, sut.getResponses().size());
            assertEquals(sut, sut.getResponses().getFirst().getSurvey());
        }
    }

    @Nested
    @DisplayName("응답 항목 추가")
    public class AddResponseItemTest{
        @Test
        @DisplayName("응답 항목 추가 성공")
        public void testAddResponseItem() {
            SurveyItem initialItem = SurveyItem.builder()
                .id(1L)
                .formType(SurveyItemFormType.TEXT)
                .build();
            ArrayList<SurveyItem> itemList = new ArrayList<>();
            itemList.add(initialItem);
            ArrayList<SurveyResponse> responseList = new ArrayList<>();

            Survey sut = Survey.builder()
                .name("Test Survey")
                .description("This is a test survey")
                .items(itemList)
                .responses(responseList)
                .build();

            SurveyResponse response = SurveyResponse.builder()
                    .id(1L)
                    .build();
            AddSurveyResponseItemCommand command = new AddSurveyResponseItemCommand(
                1L,
                1L,
                1L,
                "Test value"
            );
            response.addItem(command, SurveyItemFormType.TEXT);
            sut.addResponse(response);

            SurveyResponse result = sut.findResponse(1L).orElseThrow();
            assertEquals(1, result.getItems().size());
            SurveyResponseItem item = result.getItems().getFirst();
            assertEquals(SurveyResponseItemValue.of(SurveyItemFormType.TEXT, "Test value"), item.getValue());
        }
    }

}
