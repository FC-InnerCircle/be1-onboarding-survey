package fastcampus.innercircle.onboarding.survey.domain;

import fastcampus.innercircle.onboarding.survey.dto.request.CreateFormRequest;
import fastcampus.innercircle.onboarding.survey.dto.request.CreateQuestionOptionRequest;
import fastcampus.innercircle.onboarding.survey.dto.request.CreateQuestionRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SurveyFormTest {
    @Test
    void 폼_생성() {
        List<CreateQuestionOptionRequest> options1 = Arrays.asList(
                new CreateQuestionOptionRequest(1, "사과"),
                new CreateQuestionOptionRequest(2, "딸기"),
                new CreateQuestionOptionRequest(3, "포도")
        );
        List<CreateQuestionOptionRequest> options2 = Arrays.asList(
                new CreateQuestionOptionRequest(1, "농구"),
                new CreateQuestionOptionRequest(2, "축구")
        );

        List<CreateQuestionRequest> questions = Arrays.asList(
                new CreateQuestionRequest(1, "과일", "좋아하는 과일", true, SurveyResponseType.MULTI, options1),
                new CreateQuestionRequest(2, "운동", "좋아하는 운동", true, SurveyResponseType.SINGLE, options2)
        );

        CreateFormRequest formRequest = new CreateFormRequest("설문1", "설문 내용", questions);
        SurveyForm form = formRequest.toEntity();

        Assertions.assertThat(form.getVersion()).isEqualTo(1L);
    }
}