package fastcampus.innercircle.onboarding.survey.domain;

import fastcampus.innercircle.onboarding.survey.dto.CreateFormRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SurveyFormTest {
    @Test
    void 폼_생성() {
        List<CreateFormRequest.CreateSurveyQuestion.CreateSurveyQuestionOption> options1 = Arrays.asList(
                new CreateFormRequest.CreateSurveyQuestion.CreateSurveyQuestionOption(1, "사과"),
                new CreateFormRequest.CreateSurveyQuestion.CreateSurveyQuestionOption(2, "딸기"),
                new CreateFormRequest.CreateSurveyQuestion.CreateSurveyQuestionOption(3, "포도")
        );
        List<CreateFormRequest.CreateSurveyQuestion.CreateSurveyQuestionOption> options2 = Arrays.asList(
                new CreateFormRequest.CreateSurveyQuestion.CreateSurveyQuestionOption(1, "농구"),
                new CreateFormRequest.CreateSurveyQuestion.CreateSurveyQuestionOption(2, "축구")
        );

        List<CreateFormRequest.CreateSurveyQuestion> questions = Arrays.asList(
                new CreateFormRequest.CreateSurveyQuestion(1, "과일", "좋아하는 과일", true, SurveyResponseType.MULTI, options1),
                new CreateFormRequest.CreateSurveyQuestion(2, "운동", "좋아하는 운동", true, SurveyResponseType.SINGLE, options2)
        );

        CreateFormRequest formRequest = new CreateFormRequest("설문1", "설문 내용", questions);
        SurveyForm form = formRequest.toEntity();

        Assertions.assertThat(form.getVersion()).isEqualTo(1L);
    }
}