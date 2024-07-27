package com.fastcampus.innercircle.survey_api.service;

import com.fastcampus.innercircle.survey_api.domain.QuestionType;
import com.fastcampus.innercircle.survey_api.domain.entity.Answer;
import com.fastcampus.innercircle.survey_api.domain.entity.Response;
import com.fastcampus.innercircle.survey_api.domain.entity.SurveyForm;
import com.fastcampus.innercircle.survey_api.domain.request.AnswerRequest;
import com.fastcampus.innercircle.survey_api.domain.request.QuestionRequest;
import com.fastcampus.innercircle.survey_api.domain.request.ResponseRequest;
import com.fastcampus.innercircle.survey_api.domain.request.SurveyFormRequest;
import com.fastcampus.innercircle.survey_api.domain.response.SurveyResponse;
import com.fastcampus.innercircle.survey_api.repository.SurveyFormRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ResponseServiceTest {

    @Autowired
    private ResponseService responseService;

    @Autowired
    private SurveyFormService surveyFormService;

    @Autowired
    private SurveyFormRepository surveyFormRepository;

    @Test
    @DisplayName("설문조사 응답 제출 테스트")
    public void 설문조사응답제출_테스트() {

        // given
        SurveyFormRequest surveyFormRequest = new SurveyFormRequest("설문조사 이름", "설문조사 설명");
        /* 설문 받을 항목 */
        surveyFormRequest.getQuestions().addAll(List.of(
                new QuestionRequest(null, "항목 이름", "항목 설명", QuestionType.SHORT_ANSWER, true, null),
                new QuestionRequest(null, "항목 이름2", "항목 설명2", QuestionType.MULTIPLE_CHOICE, false, List.of("옵션1", "옵션2", "옵션3"))
        ));

        SurveyForm surveyForm = surveyFormService.saveSurveyForm(surveyFormRequest);

        ResponseRequest responseRequest = new ResponseRequest();

        AnswerRequest answerRequest1 = new AnswerRequest(
                surveyForm.getQuestions().get(0).getQuestionId(),
                "응답 내용"
        );

        AnswerRequest answerRequest2 = new AnswerRequest(
                surveyForm.getQuestions().get(1).getQuestionId(),
                "1"
        );

        responseRequest.getAnswers().addAll(List.of(answerRequest1, answerRequest2));

        // when
        Response response = responseService.submitResponse(surveyForm.getFormId(), responseRequest);

        // then
        assertNotNull(response);
        assertEquals(surveyForm.getFormId(), response.getFormId());
        assertEquals(2, response.getAnswers().size());

        Answer answer1 = response.getAnswers().get(0);
        assertEquals("항목 이름", answer1.getQuestionTitle());
        assertEquals("응답 내용", answer1.getContent());

        Answer answer2 = response.getAnswers().get(1);
        assertEquals("항목 이름2", answer2.getQuestionTitle());
        assertEquals("1", answer2.getContent());
    }

    @Test
    @DisplayName("설문조사 응답 조회 테스트")
    public void 설문조사응답조회_테스트() {

        // given
        long formId = 1L;

        // when
        List<SurveyResponse> responses = responseService.getResponses(formId);

        // then
        assertNotNull(responses);
        assertFalse(responses.isEmpty());
    }
}
