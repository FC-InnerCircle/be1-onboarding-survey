package com.innercircle.project_one;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innercircle.project_one.survey.api.SurveyController;
import com.innercircle.project_one.survey.api.SurveyService;
import com.innercircle.project_one.survey.api.dto.SurveyDTO;
import com.innercircle.project_one.survey.common.ApiResponse;
import com.innercircle.project_one.survey.common.ErrorEnum;
import com.innercircle.project_one.survey.common.ErrorResponse;
import com.innercircle.project_one.survey.common.SuccessResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SurveyController.class)
public class SurveyControllerTest {

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper mapper;

    @MockBean
    private SurveyService surveyService;


    @DisplayName("JSON을 통해 설문 조사를 생성할 수 있다.")
    @Test
    void makeSurvey_happyForAll() throws Exception {
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

        SurveyDTO survey = mapper.readValue(jsonString, SurveyDTO.class);

        // when
        ApiResponse mockResponse = new SuccessResponse<>("설문조사 폼이 저장되었습니다.");
        Mockito.when(surveyService.saveSurvey(Mockito.any(SurveyDTO.class)))
                .thenReturn(mockResponse);

        // then
        ApiResponse response = surveyService.saveSurvey(survey);
        assertEquals(HttpStatus.OK, response.getHttpStatus());
    }



    @DisplayName("설문 조사 생성 시 타입이 단일 선택 리스트인 경우 리스트 요소는 1개 이상 지정되어야 한다.")
    @Test
    void makeSurvey_ifRadioTypeElementsSmallThan100Return400() throws Exception {
        // given
        String jsonString = "{"
                + "\"title\": \"설문명\","
                + "\"description\": \"설문 설명\","
                + "\"objects\": ["
                + "  {\"type\": \"radio\", \"description\": \"설명3\", \"title\": \"단일 선택 리스트\", \"elements\": [], \"isRequired\": false}"
                + "]"
                + "}";

        SurveyDTO survey = mapper.readValue(jsonString, SurveyDTO.class);

        // when
        ApiResponse mockResponse = new ErrorResponse(ErrorEnum.INVALID_REQUEST, "선택 리스트 요소는 1개 이상 지정되어야 합니다.");
        Mockito.when(surveyService.saveSurvey(Mockito.any(SurveyDTO.class)))
                .thenReturn(mockResponse);

        // then
        ApiResponse response = surveyService.saveSurvey(survey);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }



    @DisplayName("설문 조사 생성 시 타입이 다중 선택 리스트인 경우 리스트 요소는 1개 이상 지정되어야 한다.")
    @Test
    void makeSurvey_ifCheckBoxTypeElementsSmallThan100Return400() throws Exception {
        // given
        String jsonString = "{"
                + "\"title\": \"설문명\","
                + "\"description\": \"설문 설명\","
                + "\"objects\": ["
                + "  {\"type\": \"check_box\", \"description\": \"설명4\", \"title\": \"다중 선택 리스트\", \"elements\": [], \"isRequired\": true}"
                + "]"
                + "}";

        SurveyDTO survey = mapper.readValue(jsonString, SurveyDTO.class);

        // when
        ApiResponse mockResponse = new ErrorResponse(ErrorEnum.INVALID_REQUEST, "선택 리스트 요소는 1개 이상 지정되어야 합니다.");
        Mockito.when(surveyService.saveSurvey(Mockito.any(SurveyDTO.class)))
                .thenReturn(mockResponse);

        // then
        ApiResponse response = surveyService.saveSurvey(survey);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }


    @DisplayName("설문 조사 생성 시 타입이 리스트에 없을 경우 예외를 반환한다.")
    @Test
    void makeSurvey_ifTypeIsIncorrectReturn400() throws Exception {

        String weirdType = "weirdType".toUpperCase();

        String jsonString = "{"
                + "\"name\": \"설문명\","
                + "\"description\": \"설문 설명\","
                + "\"objects\": ["
                + "  {\"type\": \"" + weirdType + "\", \"content\": {\"title\": \"다중 선택 리스트\", \"elements\": []}, \"required\": false}"
                + "]"
                + "}";

        SurveyDTO survey = mapper.readValue(jsonString, SurveyDTO.class);

        // when
        ApiResponse mockResponse = new ErrorResponse(ErrorEnum.INVALID_REQUEST, "적절한 타입이 아닙니다: " + weirdType);
        Mockito.when(surveyService.saveSurvey(Mockito.any(SurveyDTO.class)))
                .thenReturn(mockResponse);

        // then
        ApiResponse response = surveyService.saveSurvey(survey);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
    }


}
