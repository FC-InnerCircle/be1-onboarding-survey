package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.QuestionType;
import com.innercircle.onboardingservey.domain.model.SurveyCommand;
import com.innercircle.onboardingservey.domain.model.SurveyResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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

        final SurveyCommand.SurveyCreateCommand surveyCreateCommand = fixtureSurveyCreateCommand();
        final SurveyResult.SurveyDetailResult surveyDetailResult = surveyService.create(surveyCreateCommand);

        assertEqualSurvey(surveyDetailResult, surveyCreateCommand);

    }


    private SurveyCommand.SurveyCreateCommand fixtureSurveyCreateCommand() {
        return new SurveyCommand.SurveyCreateCommand(
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
    @DisplayName("survey 수정을 성공한다.")
    void survey_update_success() {
        final SurveyCommand.SurveyCreateCommand surveyCreateCommand = fixtureSurveyCreateCommand();
        final SurveyResult.SurveyDetailResult surveyDetailResult = surveyService.create(surveyCreateCommand);

        final Long surveyId = surveyDetailResult.surveyId();
        final SurveyCommand.SurveyUpdateCommand expect = new SurveyCommand.SurveyUpdateCommand(
            surveyId,
            "수정",
            "수정했찌롱",
            new ArrayList<>()
        );
        final SurveyResult.SurveyDetailResult actual = surveyService.update(expect);

        assertEqualSurvey(actual, expect);
    }

    private void assertEqualSurvey(
        SurveyResult.SurveyDetailResult actual,
        SurveyCommand.SurveyCreateCommand expect
    ) {
        assertThat(actual).isNotNull();

        // 검증
        assertThat(actual.title()).isEqualTo(expect.surveyTitle());
        assertThat(actual.description()).isEqualTo(expect.surveyDescription());

        // 설문조사의 첫 번째 질문 검증
        assertThat(actual.questionDetailResults()).hasSize(expect.questionCreateRequests()
            .size());

        final Map<Integer, SurveyCommand.QuestionCreateCommand> questionMapByDisplayOrder = expect.questionCreateRequests()
            .stream()
            .collect(Collectors.toMap(
                SurveyCommand.QuestionCreateCommand::displayOrder,
                Function.identity()
            ));

        actual.questionDetailResults()
            .forEach(question -> assertEqualQuestion(question, questionMapByDisplayOrder.get(question.displayOrder())));
    }
    private void assertEqualSurvey(
        SurveyResult.SurveyDetailResult actual,
        SurveyCommand.SurveyUpdateCommand expect
    ) {
        assertThat(actual).isNotNull();
        // 검증
        assertThat(actual.title()).isEqualTo(expect.surveyTitle());
        assertThat(actual.description()).isEqualTo(expect.surveyDescription());

        // 설문조사의 첫 번째 질문 검증
        assertThat(actual.questionDetailResults()).hasSize(expect.questionUpdateCommands()
            .size());

        final Map<Integer, SurveyCommand.QuestionUpdateCommand> questionMapByDisplayOrder = expect.questionUpdateCommands()
            .stream()
            .collect(Collectors.toMap(
                SurveyCommand.QuestionUpdateCommand::displayOrder,
                Function.identity()
            ));

        actual.questionDetailResults()
            .forEach(question -> assertEqualQuestion(question, questionMapByDisplayOrder.get(question.displayOrder())));
    }

    private void assertEqualQuestion(
        SurveyResult.QuestionDetailResult actual,
        SurveyCommand.QuestionCreateCommand expect
    ) {
        assertThat(actual).isNotNull();
        assertThat(actual.title()).isEqualTo(expect.questionTitle());
        assertThat(actual.description()).isEqualTo(expect.questionDescription());
        assertThat(actual.required()).isEqualTo(expect.required());
        assertThat(actual.questionType()).isEqualTo(expect.questionType());
    }
    private void assertEqualQuestion(
        SurveyResult.QuestionDetailResult actual,
        SurveyCommand.QuestionUpdateCommand expect
    ) {
        assertThat(actual).isNotNull();
        assertThat(actual.title()).isEqualTo(expect.questionTitle());
        assertThat(actual.description()).isEqualTo(expect.questionDescription());
        assertThat(actual.required()).isEqualTo(expect.required());
        assertThat(actual.questionType()).isEqualTo(expect.questionType());
    }
}
