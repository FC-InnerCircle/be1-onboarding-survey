package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.QuestionType;
import com.innercircle.onboardingservey.domain.model.SurveyCommand;
import com.innercircle.onboardingservey.domain.model.SurveyResults;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class SurveyServiceTest {

    @Autowired
    private SurveyService surveyService;

    String title = "test title";
    String description = "test description";

    String questionTitle = "문제 1";
    String questionDescription = "이름을 기입해주세요";
    Boolean required = Boolean.TRUE;
    QuestionType questionType = QuestionType.SHORT_TEXT;

    @Test
    @DisplayName("설문조사 생성 테스트")
    void survey_create_success() {

        final SurveyCommand.SurveyVersionCreateCommand surveyVersionCreateCommand = fixtureSurveyCreateCommand();
        final SurveyResults.SurveyResult surveyResult = surveyService.createSurvey(surveyVersionCreateCommand);

        assertEqualSurvey(
            surveyResult,
            surveyVersionCreateCommand
        );

    }


    private SurveyCommand.SurveyVersionCreateCommand fixtureSurveyCreateCommand() {
        return new SurveyCommand.SurveyVersionCreateCommand(
            title,
            description,
            List.of(
                new SurveyCommand.QuestionCreateCommand(
                    questionTitle,
                    questionDescription,
                    required,
                    questionType,
                    1,
                    null
                ),
                new SurveyCommand.QuestionCreateCommand(
                    questionTitle + 2,
                    questionDescription + 2,
                    required,
                    QuestionType.LONG_TEXT,
                    2,
                    null
                ),
                new SurveyCommand.QuestionCreateCommand(
                    questionTitle + 3,
                    null,
                    required,
                    QuestionType.SINGLE_CHOICE,
                    3,
                    List.of(
                        new SurveyCommand.QuestionOptionCreateCommand(
                            "옵션1",
                            1
                        ),
                        new SurveyCommand.QuestionOptionCreateCommand(
                            "옵션2",
                            2
                        ),
                        new SurveyCommand.QuestionOptionCreateCommand(
                            "옵션3",
                            3
                        ),
                        new SurveyCommand.QuestionOptionCreateCommand(
                            "옵션4",
                            4
                        )
                    )
                ),
                new SurveyCommand.QuestionCreateCommand(
                    questionTitle + 4,
                    null,
                    required,
                    QuestionType.MULTIPLE_CHOICE,
                    4,
                    List.of(
                        new SurveyCommand.QuestionOptionCreateCommand(
                            "옵션1",
                            1
                        ),
                        new SurveyCommand.QuestionOptionCreateCommand(
                            "옵션2",
                            2
                        ),
                        new SurveyCommand.QuestionOptionCreateCommand(
                            "옵션3",
                            3
                        ),
                        new SurveyCommand.QuestionOptionCreateCommand(
                            "옵션4",
                            4
                        )
                    )
                )
            )
        );
    }

    @Test
    @DisplayName("survey에 버전이 추가된다.")
    void survey_update_success() {
        final SurveyCommand.SurveyVersionCreateCommand surveyVersionCreateCommand = fixtureSurveyCreateCommand();
        final SurveyResults.SurveyResult surveyResult = surveyService.createSurvey(surveyVersionCreateCommand);

        final Long surveyId = surveyResult.surveyId();
        final SurveyCommand.SurveyVersionCreateCommand expect = new SurveyCommand.SurveyVersionCreateCommand(
            "수정",
            "수정했찌롱",
            new ArrayList<>()
        );
        final SurveyResults.SurveyResult actual = surveyService.addSurveyVersion(surveyId, expect);

        assertEqualSurvey(actual, expect);
    }

    private void assertEqualSurvey(
        SurveyResults.SurveyResult actual,
        SurveyCommand.SurveyVersionCreateCommand expect
    ) {
        assertThat(actual).isNotNull();

        // 검증
        assertThat(actual.title()).isEqualTo(expect.surveyTitle());
        assertThat(actual.description()).isEqualTo(expect.surveyDescription());

        // 설문조사의 첫 번째 질문 검증
        assertThat(actual.questionResults()).hasSize(expect.questionCreateRequests()
            .size());

        final Map<Integer, SurveyCommand.QuestionCreateCommand> questionMapByDisplayOrder = expect.questionCreateRequests()
            .stream()
            .collect(Collectors.toMap(
                SurveyCommand.QuestionCreateCommand::displayOrder,
                Function.identity()
            ));

        actual.questionResults()
            .forEach(question -> assertEqualQuestion(question, questionMapByDisplayOrder.get(question.displayOrder())));
    }

    private void assertEqualQuestion(
        SurveyResults.QuestionResult actual,
        SurveyCommand.QuestionCreateCommand expect
    ) {
        assertThat(actual).isNotNull();
        assertThat(actual.title()).isEqualTo(expect.questionTitle());
        assertThat(actual.description()).isEqualTo(expect.questionDescription());
        assertThat(actual.required()).isEqualTo(expect.required());
        assertThat(actual.questionType()).isEqualTo(expect.questionType());
    }
}
