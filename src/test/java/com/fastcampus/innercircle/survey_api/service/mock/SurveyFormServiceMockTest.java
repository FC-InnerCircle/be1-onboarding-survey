package com.fastcampus.innercircle.survey_api.service.mock;

import com.fastcampus.innercircle.survey_api.domain.QuestionType;
import com.fastcampus.innercircle.survey_api.domain.entity.SurveyForm;
import com.fastcampus.innercircle.survey_api.domain.request.QuestionRequest;
import com.fastcampus.innercircle.survey_api.domain.request.SurveyFormRequest;
import com.fastcampus.innercircle.survey_api.repository.SurveyFormRepository;
import com.fastcampus.innercircle.survey_api.service.SurveyFormService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SurveyFormServiceMockTest {

    @Mock
    private SurveyFormRepository surveyFormRepository;

    @InjectMocks
    private SurveyFormService surveyFormService;

    @Test
    @DisplayName("설문조사 생성 테스트")
    public void 설문조사생성_정상_테스트() {

        // given
        SurveyFormRequest surveyFormRequest = new SurveyFormRequest("설문조사 이름", "설문조사 설명");
        /* 설문 받을 항목 */
        surveyFormRequest.getQuestions().addAll(List.of(
                new QuestionRequest(null, "항목 이름", "항목 설명", QuestionType.SHORT_ANSWER, true, null),
                new QuestionRequest(null, "항목 이름2", "항목 설명2", QuestionType.MULTIPLE_CHOICE, false, List.of("옵션1", "옵션2", "옵션3"))
        ));

        // when
        when(surveyFormRepository.save(any(SurveyForm.class))).thenReturn(new SurveyForm(surveyFormRequest.getTitle(), surveyFormRequest.getDescription()));

        // then
        SurveyForm result = surveyFormService.saveSurveyForm(surveyFormRequest);
        assertEquals("설문조사 이름", result.getTitle());
        assertEquals("설문조사 설명", result.getDescription());

        verify(surveyFormRepository, times(1)).save(any(SurveyForm.class));
    }

    @Test
    @DisplayName("설문조사 수정 테스트")
    public void 설문조사수정_정상_테스트() {

        // given
        long formId = 1L;
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setTitle("설문조사 이름");
        surveyForm.setDescription("설문조사 설명");
        SurveyFormRequest request = new SurveyFormRequest("설문조사 이름 new", "설문조사 설명 new");

        // when
        when(surveyFormRepository.findById(formId)).thenReturn(Optional.of(surveyForm));

        // then
        surveyFormService.updateSurveyForm(formId, request);
        assertEquals("설문조사 이름 new", surveyForm.getTitle());
        assertEquals("설문조사 설명 new", surveyForm.getDescription());
    }
}
