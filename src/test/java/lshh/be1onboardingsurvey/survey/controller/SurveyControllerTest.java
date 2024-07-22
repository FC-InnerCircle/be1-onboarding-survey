package lshh.be1onboardingsurvey.survey.controller;

import lshh.be1onboardingsurvey.survey.domain.command.*;
import lshh.be1onboardingsurvey.survey.domain.SurveyService;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(SurveyController.class)
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SurveyService surveyService;

    @Nested
    @DisplayName("Survey 생성")
    class CreateTest{
        @Test
        @DisplayName("Survey 생성 성공")
        public void createSurvey_success() throws Exception {
            Result result = new Result<>(Result.Status.SUCCESS, null);
            Mockito.when(surveyService.create(any(CreateSurveyCommand.class))).thenReturn(result);

            // when
            mockMvc.perform(
                    MockMvcRequestBuilders.post("/survey")
                            .contentType("application/json")
                            .content("""
                                {"name": "Test Survey","description": "This is a test survey"}
                                """
                            )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("SUCCESS")));
        }

        @Test
        @DisplayName("Survey 생성 실패 - 이름 없음")
        public void createSurvey_no_name_fail() throws Exception {
            // when
            mockMvc.perform(
                    MockMvcRequestBuilders.post("/survey")
                        .contentType("application/json")
                        .content("""
                            {"name": "","description": "No Name is failed"}
                            """
                        )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")));
        }
    }

    @Nested
    @DisplayName("Survey 항목 추가")
    class AddItemTest{
        @Test
        @DisplayName("Survey 항목 추가 성공")
        public void addItem_success() throws Exception {
            Result result = new Result<>(Result.Status.SUCCESS, null);
            Mockito.when(surveyService.addItem(any(AddSurveyItemCommand.class))).thenReturn(result);

            // when
            mockMvc.perform(
                    MockMvcRequestBuilders.post("/survey/item")
                        .contentType("application/json")
                        .content("""
                            {"surveyId": 1,"name": "Test Item","description": "This is a test item","formType": "TEXT","required": true,"sequence": 1}
                            """
                        )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("SUCCESS")));
        }

        @Test
        @DisplayName("Survey 항목 추가 실패 - SurveyId 없음")
        public void addItem_fail() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/item")
                                    .contentType("application/json")
                                    .content("""
                                        {"name": "Test Item","description": "This is a test item","formType": "TEXT","required": true,"sequence": 1}
                                        """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand`, problem: SurveyId must not be null")));
        }

        @Test
        @DisplayName("Survey 항목 추가 실패 - FormType 없음")
        public void addItem_fail_no_formType() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/item")
                                    .contentType("application/json")
                                    .content("""
                                        {"surveyId": 1,"name": "Test Item","description": "This is a test item","required": true,"sequence": 1}
                                        """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand`, problem: Form must not be null")));
        }

        @Test
        @DisplayName("Survey 항목 추가 실패 - SurveyId, FormType 없음")
        public void addItem_fail_no_surveyId_formType() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/item")
                                    .contentType("application/json")
                                    .content("""
                                        {"name": "Test Item","description": "This is a test item","required": true,"sequence": 1}
                                        """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemCommand`, problem: SurveyId must not be null")));
        }

        @Test
        @DisplayName("Survey 항목 추가 실패 - 올바르지 못한 FormType")
        public void addItem_fail_invalid_formType() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/item")
                                    .contentType("application/json")
                                    .content("""
                                        {"surveyId": 1,"name": "Test Item","description": "This is a test item","formType": "INVALID","required": true,"sequence": 1}
                                        """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("""
                         JSON parse error: Cannot deserialize value of type `lshh.be1onboardingsurvey.survey.domain.SurveyItemFormType` from String "INVALID": not one of the values accepted for Enum class: [TEXTAREA, CHECKBOX, TEXT, RADIO]""")));
        }
    }

    @Nested
    @DisplayName("Survey 항목 옵션 추가")
    class AddOptionTest{
        @Test
        @DisplayName("Survey 항목 옵션 추가 성공")
        public void addOption_success() throws Exception {
            Result result = new Result<>(Result.Status.SUCCESS, null);
            Mockito.when(surveyService.addItemOption(any(AddSurveyItemOptionCommand.class))).thenReturn(result);

            // when
            mockMvc.perform(
                    MockMvcRequestBuilders.post("/survey/1/item/1/option")
                        .contentType("application/json")
                        .content("""
                            {"surveyId": 1,"itemId": 1,"name": "Test Option","description": "This is a test option","sequence": 1}
                            """
                        )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("SUCCESS")));
        }

        @Test
        @DisplayName("Survey 항목 옵션 추가 실패 - SurveyId 없음")
        public void addOption_fail() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/item/1/option")
                                    .contentType("application/json")
                                    .content("""
                                        {"name": "Test Option","description": "This is a test option","sequence": 1}
                                        """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemOptionCommand`, problem: SurveyId, itemId must not be null")));
        }

        @Test
        @DisplayName("Survey 항목 옵션 추가 실패 - ItemId 없음")
        public void addOption_fail_no_itemId() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/item/1/option")
                                    .contentType("application/json")
                                    .content("""
                                        {"surveyId": 1,"name": "Test Option","description": "This is a test option","sequence": 1}
                                        """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.AddSurveyItemOptionCommand`, problem: SurveyId, itemId must not be null")));
        }

    }

    @Nested
    @DisplayName("Survey 항목 수정")
    class UpdateItemTest {
        @Test
        @DisplayName("Survey 항목 수정 성공")
        public void updateItem_success() throws Exception {
            Result result = new Result<>(Result.Status.SUCCESS, null);
            Mockito.when(surveyService.updateItem(any(UpdateSurveyItemCommand.class))).thenReturn(result);

            // when
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/item/1")
                                    .contentType("application/json")
                                    .content("""
                                            {"surveyId": 1,"itemId": 1,"name": "Test Item","description": "This is a test item","formType": "TEXT","required": true,"sequence": 1}
                                            """
                                    )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("SUCCESS")));
        }

        @Test
        @DisplayName("Survey 항목 수정 실패 - SurveyId 없음")
        public void updateItem_fail() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/item/1")
                                    .contentType("application/json")
                                    .content("""
                                            {"itemId": 1,"name": "Test Item","description": "This is a test item","formType": "TEXT","required": true,"sequence": 1}
                                            """
                                    )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.UpdateSurveyItemCommand`, problem: SurveyId, itemId must not be null")));
        }

        @Test
        @DisplayName("Survey 항목 수정 실패 - ItemId 없음")
        public void updateItem_fail_no_itemId() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/item/1")
                                    .contentType("application/json")
                                    .content("""
                                            {"surveyId": 1,"name": "Test Item","description": "This is a test item","formType": "TEXT","required": true,"sequence": 1}
                                            """
                                    )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.UpdateSurveyItemCommand`, problem: SurveyId, itemId must not be null")));
        }
    }

    @Nested
    @DisplayName("Survey 항목 옵션 수정")
    class UpdateOptionTest {
        @Test
        @DisplayName("Survey 항목 옵션 수정 성공")
        public void updateOption_success() throws Exception {
            Result result = new Result<>(Result.Status.SUCCESS, null);
            Mockito.when(surveyService.updateItemOption(any(UpdateSurveyItemOptionCommand.class))).thenReturn(result);

            // when
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/item/1/option/1")
                                    .contentType("application/json")
                                    .content("""
                                            {"surveyId": 1,"itemId": 1,"optionId": 1,"name": "Test Option","description": "This is a test option","sequence": 1}
                                            """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("SUCCESS")));
        }

        @Test
        @DisplayName("Survey 항목 옵션 수정 실패 - SurveyId 없음")
        public void updateOption_fail() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/item/1/option/1")
                                    .contentType("application/json")
                                    .content("""
                                            {"itemId": 1,"optionId": 1,"name": "Test Option","description": "This is a test option","sequence": 1}
                                            """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.UpdateSurveyItemOptionCommand`, problem: SurveyId, itemId, optionId must not be null")));
        }

        @Test
        @DisplayName("Survey 항목 옵션 수정 실패 - ItemId 없음")
        public void updateOption_fail_no_itemId() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/item/1/option/1")
                                    .contentType("application/json")
                                    .content("""
                                            {"surveyId": 1,"optionId": 1,"name": "Test Option","description": "This is a test option","sequence": 1}
                                            """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.UpdateSurveyItemOptionCommand`, problem: SurveyId, itemId, optionId must not be null")));
        }

        @Test
        @DisplayName("Survey 항목 옵션 수정 실패 - OptionId 없음")
        public void updateOption_fail_no_optionId() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/item/1/option/1")
                                    .contentType("application/json")
                                    .content("""
                                            {"surveyId": 1,"itemId": 1,"name": "Test Option","description": "This is a test option","sequence": 1}
                                            """)
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.UpdateSurveyItemOptionCommand`, problem: SurveyId, itemId, optionId must not be null")));
        }
    }


    @Nested
    @DisplayName("Survey 응답 추가")
    class AddResponseTest{
        @Test
        @DisplayName("Survey 응답 추가 성공")
        public void addResponse_success() throws Exception {
            Result result = new Result<>(Result.Status.SUCCESS, null);
            Mockito.when(surveyService.addResponse(any(AddSurveyResponseCommand.class))).thenReturn(result);

            // when
            mockMvc.perform(
                    MockMvcRequestBuilders.post("/survey/1/response")
                        .contentType("application/json")
                        .content("""
                            {"surveyId": 1}
                            """
                        )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("SUCCESS")));
        }

        @Test
        @DisplayName("Survey 응답 추가 실패 - SurveyId 없음")
        public void addResponse_fail() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/response")
                                    .contentType("application/json")
                                    .content("""
                                        {}
                                        """
                                    )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.AddSurveyResponseCommand`, problem: SurveyId must not be null")));
        }
    }

    @Nested
    @DisplayName("Survey 응답 항목 추가")
    class AddResponseItemTest {
        @Test
        @DisplayName("Survey 응답 항목 추가 성공")
        public void addResponseItem_success() throws Exception {
            Result result = new Result<>(Result.Status.SUCCESS, null);
            Mockito.when(surveyService.addResponseItem(any(AddSurveyResponseItemCommand.class))).thenReturn(result);

            // when
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/response/1/item")
                                    .contentType("application/json")
                                    .content("""

                                            {"surveyId": 1,"responseId": 1,"itemId": 1,"value": "Test Value"}
                            """
                        )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("SUCCESS")));
        }

        @Test
        @DisplayName("Survey 응답 항목 추가 실패 - SurveyId 없음")
        public void addResponseItem_fail() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/response/1/item")
                                    .contentType("application/json")
                                    .content("""
                                        {"responseId": 1,"itemId": 1,"value": "Test Value"}
                                        """
                                    )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.AddSurveyResponseItemCommand`, problem: SurveyId, responseId, itemId must not be null")));
        }

        @Test
        @DisplayName("Survey 응답 항목 추가 실패 - itemId 없음")
        public void addResponseItem_fail_no_itemId() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/response/1/item")
                                    .contentType("application/json")
                                    .content("""
                                        {"surveyId": 1,"responseId": 1,"value": "Test Value"}
                                        """
                                    )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.AddSurveyResponseItemCommand`, problem: SurveyId, responseId, itemId must not be null")));
        }

        @Test
        @DisplayName("Survey 응답 항목 추가 실패 - ResponseId 없음")
        public void addResponseItem_fail_no_responseId() throws Exception {
            mockMvc.perform(
                            MockMvcRequestBuilders.post("/survey/1/response/1/item")
                                    .contentType("application/json")
                                    .content("""

                                            {"surveyId": 1,"itemId": 1,"value": "Test Value"}
                                        """
                                    )
                    )
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.status", equalTo("FAIL")))
                    .andExpect(jsonPath("$.data", equalTo("JSON parse error: Cannot construct instance of `lshh.be1onboardingsurvey.survey.domain.command.AddSurveyResponseItemCommand`, problem: SurveyId, responseId, itemId must not be null")));
        }
    }
}