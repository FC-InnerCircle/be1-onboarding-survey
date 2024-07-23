package lshh.be1onboardingsurvey.survey.domain;

import lshh.be1onboardingsurvey.survey.domain.command.*;
import lshh.be1onboardingsurvey.survey.domain.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

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
            SurveyView surveyView = surveyService.findByName("testCreate_Simple").getFirst();
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
            SurveyView surveyView = surveyService.findByName("testAddItem_Simple").getFirst();
            Long surveyId = surveyView.id();

            // Act
            AddSurveyItemCommand command = new AddSurveyItemCommand(surveyId, "testAddItem_Simple", "description", SurveyItemFormType.TEXT, true, 1L);
            Result<?> result = surveyService.addItem(command);

            // Assert
            assertEquals(Result.success(), result);
            SurveyView surveyWithItem = surveyService.findByName("testAddItem_Simple").getFirst();
            System.out.println(surveyWithItem.items());
            assertEquals(1, surveyWithItem.items().size());
        }
    }

    @Nested
    @DisplayName("Survey 항목 수정")
    class UpdateItemTest{
        @Test
        @DisplayName("간단한 항목 수정")
        public void testUpdateItem_Simple() {
            // Arrange
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("survey_testUpdateItem_Simple", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("survey_testUpdateItem_Simple").getFirst();
            Long surveyId = surveyView.id();
            System.out.println(surveyView);
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "item_testUpdateItem_Simple", "description", SurveyItemFormType.RADIO, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyViewWithItem = surveyService.findByName("survey_testUpdateItem_Simple").getFirst();
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
            SurveyView updatedSurvey = surveyService.findByName("survey_testUpdateItem_Simple").getFirst();
            System.out.println(updatedSurvey);
            assertEquals("item_updated_testUpdateItem_Simple", updatedSurvey.items().getFirst().name());
        }

        @Test
        @DisplayName("항목 수정 후, 옵션이 복사되어, 양쪽에 전부 남아있는지 버전 확인")
        public void testUpdateItem_CheckOptionCopy() {
            // Arrange
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("survey_testUpdateItem_CheckOptionCopy", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("survey_testUpdateItem_CheckOptionCopy").getFirst();
            Long surveyId = surveyView.id();
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "item_testUpdateItem_CheckOptionCopy", "description", SurveyItemFormType.RADIO, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyViewWithItem = surveyService.findByName("survey_testUpdateItem_CheckOptionCopy").getFirst();
            Long itemId = surveyViewWithItem.items().getFirst().id();
            // survey item option 생성
            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(surveyId, itemId, "option_testUpdateItem_CheckOptionCopy", "description", 1L);
            surveyService.addItemOption(command);

            // Act
            UpdateSurveyItemCommand updateSurveyItemCommand = new UpdateSurveyItemCommand(
                    surveyId,
                    itemId,
                    "item_updated_testUpdateItem_CheckOptionCopy",
                    "description",
                    SurveyItemFormType.RADIO,
                    true,
                    2L
            );
            Result<?> result = surveyService.updateItem(updateSurveyItemCommand);

            // Assert
            assertEquals(Result.success(), result);
            SurveyAllVersionView updatedSurvey = surveyService.findWithAllVersion(surveyId).orElseThrow();
            assertEquals(2, updatedSurvey.items().size());
            SurveyItemVersionView latest = updatedSurvey.items().stream().filter(i->i.overridden() == null).findFirst().orElseThrow();
            SurveyItemVersionView origin = updatedSurvey.items().stream().filter(i->i.overridden() != null && i.id().equals(latest.preId())).findFirst().orElseThrow();
            System.out.println(latest);
            System.out.println(origin);
            assertEquals(1, latest.options().size());
            assertEquals(1, origin.options().size());
            assertEquals(latest.options().getFirst().name(), origin.options().getFirst().name());
        }

        @Test
        @DisplayName("한 항목을 여러번 수정하였을 경우, 두 버전은 쓰인 순서대로 버저닝된다.")
        public void testUpdateItem_WithManyTimes() throws InterruptedException {
            // Arrange
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("survey_testUpdateItem_WithManyTimes", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("survey_testUpdateItem_WithManyTimes").getFirst();
            Long surveyId = surveyView.id();
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "item_testUpdateItem_WithManyTimes", "description", SurveyItemFormType.RADIO, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyViewWithItem = surveyService.findByName("survey_testUpdateItem_WithManyTimes").getFirst();
            Long itemId = surveyViewWithItem.items().getFirst().id();

            // Act
            int testCount = 2;
            List<UpdateSurveyItemCommand> commands = IntStream.range(0, testCount).mapToObj(i -> new UpdateSurveyItemCommand(
                    surveyId,
                    itemId,
                    "item_updated_testUpdateItem_WithManyTimes_" + i,
                    "description",
                    SurveyItemFormType.RADIO,
                    true,
                    2L
            )).toList();
            IntStream.range(0, testCount).forEach(i -> {
                surveyService.updateItem(commands.get(i));
            });

            // Assert
            SurveyAllVersionView updatedSurvey = surveyService.findWithAllVersion(surveyId).orElseThrow();
            assertEquals(testCount + 1, updatedSurvey.items().size());
            List<SurveyItemVersionView> allVersions = updatedSurvey.items();
            System.out.println(allVersions);
            assertEquals(1, allVersions.stream().filter(i -> i.overridden() == null).count());
            assertEquals(testCount, allVersions.stream().filter(i -> i.overridden() != null).count());
            // 순차적으로 preid로 버저닝 되었는가 확인
            for (int i = 0; i < testCount; i++) {
                assertEquals(allVersions.get(i).id(), allVersions.get(i + 1).preId());
            }
        }

        @Test
        @DisplayName("동시에 한 항목을 수정하였을 경우, 두 버전은 쓰인 순서대로 버저닝된다.")
        public void testUpdateItem_withConcurrency() {
            // Arrange
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("survey_testUpdateItem_withConcurrency", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("survey_testUpdateItem_withConcurrency").getFirst();
            Long surveyId = surveyView.id();
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "item_testUpdateItem_withConcurrency", "description", SurveyItemFormType.RADIO, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyViewWithItem = surveyService.findByName("survey_testUpdateItem_withConcurrency").getFirst();
            Long itemId = surveyViewWithItem.items().getFirst().id();
            // survey item option 생성
            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(surveyId, itemId, "option_testUpdateItem_withConcurrency", "description", 1L);
            surveyService.addItemOption(command);

            // Act
            int testCount = 3;
            List<UpdateSurveyItemCommand> commands = IntStream.range(0, testCount).mapToObj(i -> new UpdateSurveyItemCommand(
                    surveyId,
                    itemId,
                    "item_updated_testUpdateItem_withConcurrency_" + i,
                    "description",
                    SurveyItemFormType.RADIO,
                    true,
                    2L
            )).toList();

            try (ExecutorService executorService = Executors.newFixedThreadPool(testCount)) {
                IntStream.range(0, testCount).forEach(i -> {
                    executorService.execute(() -> {
                        surveyService.updateItem(commands.get(i));
                    });
                });
                executorService.shutdown();
            }

            // Assert
            SurveyAllVersionView updatedSurvey = surveyService.findWithAllVersion(surveyId).orElseThrow();
            assertEquals(testCount + 1, updatedSurvey.items().size());
            List<SurveyItemVersionView> allVersions = updatedSurvey.items();
            System.out.println(allVersions);
            assertEquals(1, allVersions.stream().filter(i -> i.overridden() == null).count());
            assertEquals(testCount, allVersions.stream().filter(i -> i.overridden() != null).count());
            // 순차적으로 preid로 버저닝 되었는가 확인
            for (int i = 0; i < testCount; i++) {
                assertEquals(allVersions.get(i).id(), allVersions.get(i + 1).preId());
            }
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
            SurveyView surveyView = surveyService.findByName("testAddItemOption_Simple").getFirst();
            Long surveyId = surveyView.id();
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "testAddItemOption_Simple", "description", SurveyItemFormType.RADIO, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyViewWithItem = surveyService.findByName("testAddItemOption_Simple").getFirst();
            Long itemId = surveyViewWithItem.items().getFirst().id();

            // Act
            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(surveyId, itemId, "testAddItemOption_Simple", "description", 1L);
            Result<?> result = surveyService.addItemOption(command);

            // Assert
            assertEquals(Result.success(), result);
            SurveyView surveyWithItemOption = surveyService.findByName("testAddItemOption_Simple").getFirst();
            assertEquals(1, surveyWithItemOption.items().getFirst().options().size());
        }
    }

    @Nested
    @DisplayName("Survey 항목 옵션 수정")
    class UpdateItemOptionTest {
        @Test
        @DisplayName("간단한 항목 옵션 수정")
        public void testUpdateItemOption_Simple() {
            // Arrange
            // survey creation
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("testUpdateItemOption_Simple", "Description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("testUpdateItemOption_Simple").getFirst();
            Long surveyId = surveyView.id();
            // survey item creation
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "testUpdateItemOption_Simple", "Description", SurveyItemFormType.RADIO, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyViewWithItem = surveyService.findByName("testUpdateItemOption_Simple").getFirst();
            Long itemId = surveyViewWithItem.items().getFirst().id();
            // survey item option creation
            AddSurveyItemOptionCommand addSurveyItemOptionCommand = new AddSurveyItemOptionCommand(surveyId, itemId, "option_testUpdateItemOption_Simple", "Description", 1L);
            surveyService.addItemOption(addSurveyItemOptionCommand);
            SurveyView surveyViewWithItemOption = surveyService.findByName("testUpdateItemOption_Simple").getFirst();
            Long optionId = surveyViewWithItemOption.items().getFirst().options().getFirst().id();
            System.out.println(surveyViewWithItemOption);

            // Act
            UpdateSurveyItemOptionCommand updateSurveyItemOptionCommand = new UpdateSurveyItemOptionCommand(
                    surveyId,
                    itemId,
                    optionId,
                    "option_updated_testUpdateItemOption_simple",
                    "Description Updated",
                    1L
            );
            Result<?> result = surveyService.updateItemOption(updateSurveyItemOptionCommand);

            // Assert
            assertEquals(Result.Status.SUCCESS, result.status());
            SurveyView surveyWithUpdatedItemOption = surveyService.findByName("testUpdateItemOption_Simple").getFirst();
            System.out.println(surveyWithUpdatedItemOption);
            assertEquals("option_updated_testUpdateItemOption_simple", surveyWithUpdatedItemOption.items().getFirst().options().getFirst().name());
            assertEquals("Description Updated", surveyWithUpdatedItemOption.items().getFirst().options().getFirst().description());
        }

        @Test
        @DisplayName("항목 옵션 수정 후, 버전 정상 반영되었는지 확인")
        public void testUpdateItemOption_CheckVersion() {
            // Arrange
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("survey_testUpdateItemOption_CheckOptionCopy", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("survey_testUpdateItemOption_CheckOptionCopy").getFirst();
            Long surveyId = surveyView.id();
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "item_testUpdateItemOption_CheckOptionCopy", "description", SurveyItemFormType.RADIO, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyViewWithItem = surveyService.findByName("survey_testUpdateItemOption_CheckOptionCopy").getFirst();
            Long itemId = surveyViewWithItem.items().getFirst().id();
            // survey item option 생성
            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(surveyId, itemId, "option_testUpdateItemOption_CheckOptionCopy", "i'll check by description", 1L);
            surveyService.addItemOption(command);
            Long optionId = surveyService.findByName("survey_testUpdateItemOption_CheckOptionCopy").getFirst().items().getFirst().options().getFirst().id();

            // Act
            UpdateSurveyItemOptionCommand updateSurveyItemOptionCommand = new UpdateSurveyItemOptionCommand(
                    surveyId,
                    itemId,
                    optionId,
                    "option_updated_testUpdateItemOption_CheckOptionCopy",
                    "i'll check by description",
                    2L
            );
            Result<?> result = surveyService.updateItemOption(updateSurveyItemOptionCommand);

            // Assert
            assertEquals(Result.success(), result);
            SurveyAllVersionView updatedSurvey = surveyService.findWithAllVersion(surveyId).orElseThrow();
            assertEquals(2, updatedSurvey.items().getFirst().options().size());
            SurveyItemOptionVersionView latest = updatedSurvey.items().getFirst().options().stream().filter(i->i.overridden() == null).findFirst().orElseThrow();
            SurveyItemOptionVersionView origin = updatedSurvey.items().getFirst().options().stream().filter(i->i.overridden() != null && i.id().equals(latest.preId())).findFirst().orElseThrow();
            System.out.println(latest);
            System.out.println(origin);
            assertEquals(latest.description(), origin.description());
        }

        @Test
        @DisplayName("한 항목 옵션을 여러번 수정하였을 경우, 두 버전은 쓰인 순서대로 버저닝된다.")
        public void testUpdateItemOption_WithManyTimes(){
            // Arrange
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("survey_testUpdateItemOption_WithManyTimes", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("survey_testUpdateItemOption_WithManyTimes").getFirst();
            Long surveyId = surveyView.id();
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "item_testUpdateItemOption_WithManyTimes", "description", SurveyItemFormType.RADIO, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyViewWithItem = surveyService.findByName("survey_testUpdateItemOption_WithManyTimes").getFirst();
            Long itemId = surveyViewWithItem.items().getFirst().id();
            // survey item option 생성
            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(surveyId, itemId, "option_testUpdateItemOption_WithManyTimes", "description", 1L);
            surveyService.addItemOption(command);
            Long optionId = surveyService.findByName("survey_testUpdateItemOption_WithManyTimes").getFirst().items().getFirst().options().getFirst().id();

            // Act
            int testCount = 2;
            List<UpdateSurveyItemOptionCommand> commands = IntStream.range(0, testCount).mapToObj(i -> new UpdateSurveyItemOptionCommand(
                    surveyId,
                    itemId,
                    optionId,
                    "option_updated_testUpdateItemOption_WithManyTimes_" + i,
                    "description",
                    2L
            )).toList();
            IntStream.range(0, testCount).forEach(i -> {
                surveyService.updateItemOption(commands.get(i));
            });

            // Assert
            SurveyAllVersionView updatedSurvey = surveyService.findWithAllVersion(surveyId).orElseThrow();
            assertEquals(testCount + 1, updatedSurvey.items().getFirst().options().size());
            List<SurveyItemOptionVersionView> allVersions = updatedSurvey.items().getFirst().options();
            System.out.println(allVersions);
            assertEquals(1, allVersions.stream().filter(i -> i.overridden() == null).count());
            assertEquals(testCount, allVersions.stream().filter(i -> i.overridden() != null).count());
            // 순차적으로 preid로 버저닝 확인
            for (int i = 0; i < testCount; i++) {
                assertEquals(allVersions.get(i).id(), allVersions.get(i + 1).preId());
            }
        }

        @Test
        @DisplayName("동시에 한 항목 옵션을 수정하였을 경우, 두 버전은 쓰인 순서대로 버저닝된다.")
        public void testUpdateItemOption_withConcurrency() {
            // Arrange
            // survey 생성
            CreateSurveyCommand createSurveyCommand = new CreateSurveyCommand("survey_testUpdateItemOption_withConcurrency", "description");
            surveyService.create(createSurveyCommand);
            SurveyView surveyView = surveyService.findByName("survey_testUpdateItemOption_withConcurrency").getFirst();
            Long surveyId = surveyView.id();
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "item_testUpdateItemOption_withConcurrency", "description", SurveyItemFormType.RADIO, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyViewWithItem = surveyService.findByName("survey_testUpdateItemOption_withConcurrency").getFirst();
            Long itemId = surveyViewWithItem.items().getFirst().id();
            // survey item option 생성
            AddSurveyItemOptionCommand command = new AddSurveyItemOptionCommand(surveyId, itemId, "option_testUpdateItemOption_withConcurrency", "description", 1L);
            surveyService.addItemOption(command);
            Long optionId = surveyService.findByName("survey_testUpdateItemOption_withConcurrency").getFirst().items().getFirst().options().getFirst().id();

            // Act
            int testCount = 3;
            List<UpdateSurveyItemOptionCommand> commands = IntStream.range(0, testCount).mapToObj(i -> new UpdateSurveyItemOptionCommand(
                    surveyId,
                    itemId,
                    optionId,
                    "option_updated_testUpdateItemOption_withConcurrency_" + i,
                    "description",
                    2L
            )).toList();

            try (ExecutorService executorService = Executors.newFixedThreadPool(testCount)) {
                IntStream.range(0, testCount).forEach(i -> {
                    executorService.execute(() -> {
                        surveyService.updateItemOption(commands.get(i));
                    });
                });
                executorService.shutdown();
            }

            // Assert
            SurveyAllVersionView updatedSurvey = surveyService.findWithAllVersion(surveyId).orElseThrow();
            assertEquals(testCount + 1, updatedSurvey.items().getFirst().options().size());
            List<SurveyItemOptionVersionView> allVersions = updatedSurvey.items().getFirst().options();
            System.out.println(allVersions);
            assertEquals(1, allVersions.stream().filter(i -> i.overridden() == null).count());
            assertEquals(testCount, allVersions.stream().filter(i -> i.overridden() != null).count());
            // 순차적으로 preid로 버저닝 확인
            for (int i = 0; i < testCount; i++) {
                assertEquals(allVersions.get(i).id(), allVersions.get(i + 1).preId());
            }
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
            SurveyView surveyView = surveyService.findByName("testAddResponse_Simple").getFirst();
            Long surveyId = surveyView.id();
            AddSurveyResponseCommand command = new AddSurveyResponseCommand(surveyId);

            // Act
            Result<?> result = surveyService.addResponse(command);

            // Assert
            assertEquals(Result.success(), result);
            SurveyResponseView responseView = surveyService.findResponses(surveyId).getFirst();
            assertEquals(surveyId, responseView.surveyId());
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
            SurveyView surveyView = surveyService.findByName("testAddResponseItem_Simple").getFirst();
            Long surveyId = surveyView.id();
            // survey item 생성
            AddSurveyItemCommand addSurveyItemCommand = new AddSurveyItemCommand(surveyId, "testAddResponseItem_Simple", "description", SurveyItemFormType.TEXT, true, 1L);
            surveyService.addItem(addSurveyItemCommand);
            SurveyView surveyView2 = surveyService.findByName("testAddResponseItem_Simple").getFirst();
            Long surveyItemId = surveyView2.items().getFirst().id();
            // survey response 생성
            AddSurveyResponseCommand addSurveyResponseCommand = new AddSurveyResponseCommand(surveyId);
            surveyService.addResponse(addSurveyResponseCommand);
            SurveyResponseView responseView = surveyService.findResponses(surveyId).getFirst();

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
            SurveyResponseView responseView2 = surveyService.findResponses(surveyId).getFirst();
            SurveyResponseItemView itemView = responseView2.items().getFirst();
            assertEquals("item_testAddResponseItem_Simple", itemView.value());
        }
    }

}
