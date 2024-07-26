package com.fastcampus.innercircle.survey_api.service;

import com.fastcampus.innercircle.survey_api.domain.QuestionType;
import com.fastcampus.innercircle.survey_api.domain.entity.Question;
import com.fastcampus.innercircle.survey_api.domain.entity.SurveyForm;
import com.fastcampus.innercircle.survey_api.domain.request.QuestionRequest;
import com.fastcampus.innercircle.survey_api.domain.request.SurveyFormRequest;
import com.fastcampus.innercircle.survey_api.repository.SurveyFormRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SurveyFormServiceTest {

    @Autowired
    private SurveyFormService surveyFormService;

    @Autowired
    private SurveyFormRepository surveyFormRepository;

    @Test
    @DisplayName("설문조사 생성 테스트")
    public void 설문조사생성_테스트() {

        // given
        SurveyFormRequest surveyFormRequest = new SurveyFormRequest("설문조사 이름", "설문조사 설명");
        /* 설문 받을 항목 */
        surveyFormRequest.getQuestions().addAll(List.of(
                new QuestionRequest(null, "항목 이름", "항목 설명", QuestionType.SHORT_ANSWER, true, null),
                new QuestionRequest(null, "항목 이름2", "항목 설명2", QuestionType.MULTIPLE_CHOICE, false, List.of("옵션1", "옵션2", "옵션3"))
        ));

        // when
        SurveyForm result = surveyFormService.saveSurveyForm(surveyFormRequest);

        // then
        assertEquals("설문조사 이름", result.getTitle());
        assertEquals("설문조사 설명", result.getDescription());
        assertEquals(2, result.getQuestions().size());
    }

    @Test
    @DisplayName("설문조사 수정 테스트")
    public void 설문조사수정_테스트() {

        // given
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setTitle("설문조사 이름");
        surveyForm.setDescription("설문조사 설명");
        /* 설문 받을 항목 */
        Question question = new Question();
        question.setTitle("항목 이름");
        question.setDescription("항목 설명");
        question.setType(QuestionType.SHORT_ANSWER);
        question.setRequired(true);
        surveyForm.addQuestions(List.of(question));

        surveyFormRepository.save(surveyForm);

        SurveyFormRequest surveyFormRequest = new SurveyFormRequest("설문조사 이름 new", "설문조사 설명 new");

        // 변경
        QuestionRequest updatedQuestion = new QuestionRequest(question.getQuestionId(), "항목 이름 new", "항목 설명 new", QuestionType.LONG_ANSWER, false, null);

        // 추가
        QuestionRequest newQuestion = new QuestionRequest(null, "항목 이름 new", "항목 설명 new", QuestionType.SINGLE_CHOICE, true, List.of("옵션1", "옵션2", "옵션3"));

        surveyFormRequest.getQuestions().addAll(List.of(updatedQuestion, newQuestion));

        // when
        SurveyForm result = surveyFormService.updateSurveyForm(surveyForm.getFormId(), surveyFormRequest);

        // then
        assertEquals("설문조사 이름 new", result.getTitle());
        assertEquals("설문조사 설명 new", result.getDescription());
        assertEquals(2, result.getQuestions().size());
    }
}
