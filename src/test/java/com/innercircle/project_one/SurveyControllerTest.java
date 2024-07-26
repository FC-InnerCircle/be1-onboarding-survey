package com.innercircle.project_one;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innercircle.project_one.survey.api.SurveyController;
import com.innercircle.project_one.survey.api.SurveyService;
import com.innercircle.project_one.survey.api.dto.SurveyDTO;
import com.innercircle.project_one.survey.api.repository.SurveyRepository;
import com.innercircle.project_one.survey.common.ApiResponse;
import com.innercircle.project_one.survey.common.SuccessResponse;
import com.innercircle.project_one.survey.domain.Survey;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SurveyController.class)
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private SurveyService surveyService;

    @MockBean
    private SurveyRepository surveyRepository;


    @Test
    public void testCreateSurvey() throws Exception {

        // given
        String jsonString = "{"
                + "\"title\": \"설문명\","
                + "\"description\": \"설문 설명\","
                + "\"objects\": ["
                + "  {\"type\": \"text\", \"description\": \"설명1\", \"title\": \"단답형\", \"isRequired\": true},"
                + "  {\"type\": \"rich_text\", \"description\": \"설명2\", \"title\": \"장문형\", \"isRequired\": true},"
                + "  {\"type\": \"radio\", \"description\": \"설명3\", \"title\": \"단일 선택 리스트\", \"elements\": [\"element1\", \"element2\"], \"isRequired\": false},"
                + "  {\"type\": \"check_box\", \"description\": \"설명4\", \"title\": \"다중 선택 리스트\", \"elements\": [\"element1\", \"element2\"], \"isRequired\": true}"
                + "]"
                + "}";

        // when
        SurveyDTO survey = mapper.readValue(jsonString, SurveyDTO.class);
        ApiResponse apiResponse = new SuccessResponse<>("설문조사 폼이 저장되었습니다.");
        Mockito.when(surveyService.saveSurvey(survey)).thenReturn(apiResponse);

        // then
        mockMvc.perform(post("/surveys")
                        .contentType("application/json")
                        .content(jsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("설문조사 폼이 저장되었습니다."));
    }

    @Test
    public void testModifySurvey() throws Exception {

        String jsonString = "{"
                + "\"version\" : \"1\","
                + "\"title\": \"설문명2\","
                + "\"description\": \"설문 설명2\","
                + "\"objects\" : ["
                + "    {\"type\": \"text\", \"description\": \"설명2\", \"title\": \"단답형1\", \"isRequired\": true},"
                + "    {\"type\": \"rich_text\", \"description\": \"설명5\", \"title\": \"장문형3\", \"isRequired\": true},"
                + "    {\"type\": \"check_box\", \"description\": \"설명4\", \"title\": \"다중 선택 리스트\", \"elements\": [\"element\", \"element2\"], \"isRequired\": true},"
                + "    {\"type\": \"radio\", \"description\": \"설명3\", \"title\": \"단일 선택 리스트2\", \"elements\": [\"element\", \"element2\"], \"isRequired\": false}"
                + "]"
                + "}";

        Survey survey = new Survey();
        Mockito.when(surveyRepository.findById(anyLong())).thenReturn(java.util.Optional.of(survey));
        SurveyDTO surveyDTO = mapper.readValue(jsonString, SurveyDTO.class);

        ApiResponse apiResponse = new SuccessResponse<>("설문조사 폼이 업데이트되었습니다.");
        Mockito.when(surveyService.updateSurvey(1L, surveyDTO)).thenReturn(apiResponse);

        // then
        mockMvc.perform(patch("/surveys/1")
                        .contentType("application/json")
                        .content(jsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("설문조사 폼이 업데이트되었습니다."));
    }
}