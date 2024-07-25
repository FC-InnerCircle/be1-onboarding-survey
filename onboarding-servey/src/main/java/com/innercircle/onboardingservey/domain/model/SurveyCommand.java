package com.innercircle.onboardingservey.domain.model;

import com.innercircle.onboardingservey.controller.survey.SurveyRequest.QuestionCreateRequest;
import com.innercircle.onboardingservey.controller.survey.SurveyRequest.QuestionOptionCreateRequest;
import com.innercircle.onboardingservey.controller.survey.SurveyRequest.QuestionOptionUpdateRequest;
import com.innercircle.onboardingservey.controller.survey.SurveyRequest.QuestionUpdateRequest;
import com.innercircle.onboardingservey.controller.survey.SurveyRequest.SurveyCreateRequest;
import com.innercircle.onboardingservey.controller.survey.SurveyRequest.SurveyUpdateRequest;

import java.util.List;
import java.util.stream.IntStream;

public class SurveyCommand {

    public record SurveyCreateCommand(
        String surveyTitle,
        String surveyDescription,
        List<QuestionCreateCommand> questionCreateRequests
    ) {

        public static SurveyCommand.SurveyCreateCommand from(SurveyCreateRequest surveyCreateRequest) {
            return new SurveyCreateCommand(
                surveyCreateRequest.surveyTitle(),
                surveyCreateRequest.surveyDescription(),
                IntStream.range(
                        0,
                        surveyCreateRequest.questionCreateRequests().size()
                    )
                    .mapToObj(i ->
                        QuestionCreateCommand.from(
                            surveyCreateRequest.questionCreateRequests().get(i),
                            i
                        ))
                    .toList()
            );
        }
    }

    public record SurveyUpdateCommand(
        Long surveyId,
        String surveyTitle,
        String surveyDescription,
        List<QuestionUpdateCommand> questionUpdateCommands
    ) {

        public static SurveyCommand.SurveyUpdateCommand from(SurveyUpdateRequest surveyUpdateRequest) {
            return new SurveyUpdateCommand(
                surveyUpdateRequest.surveyId(),
                surveyUpdateRequest.surveyTitle(),
                surveyUpdateRequest.surveyDescription(),
                IntStream.range(
                        0,
                        surveyUpdateRequest.questionUpdateRequests().size()
                    )
                    .mapToObj(i ->
                        QuestionUpdateCommand.from(
                            surveyUpdateRequest.questionUpdateRequests().get(i),
                            i
                        ))
                    .toList()
            );
        }
    }

    public record QuestionCreateCommand(
        String questionTitle,
        String questionDescription,
        Boolean required,
        QuestionType questionType,
        Integer displayOrder,
        List<QuestionOptionCreateCommand> questionOptionCreateCommands
    ) {

        public static QuestionCreateCommand from(
            QuestionCreateRequest questionCreateRequest,
            Integer displayOrder
        ) {
            return new QuestionCreateCommand(
                questionCreateRequest.questionTitle(),
                questionCreateRequest.questionDescription(),
                questionCreateRequest.isRequired(),
                QuestionType.valueOf(questionCreateRequest.questionType()),
                displayOrder,
                IntStream.range(
                        0,
                        questionCreateRequest.questionOptionCreateRequests()
                            .size()
                    )
                    .mapToObj(i -> QuestionOptionCreateCommand.from(
                        questionCreateRequest.questionOptionCreateRequests()
                            .get(i),
                        i
                    ))
                    .toList()
            );
        }
    }

    public record QuestionUpdateCommand(
        Long questionId,
        String questionTitle,
        String questionDescription,
        Boolean required,
        QuestionType questionType,
        Integer displayOrder,
        List<QuestionOptionUpdateCommand> questionOptionUpdateCommands
    ) {

        public static QuestionUpdateCommand from(QuestionUpdateRequest questionUpdateRequest, Integer displayOrder) {
            return new QuestionUpdateCommand(
                questionUpdateRequest.questionId(),
                questionUpdateRequest.questionTitle(),
                questionUpdateRequest.questionDescription(),
                questionUpdateRequest.isRequired(),
                QuestionType.valueOf(questionUpdateRequest.questionType()),
                displayOrder,
                IntStream.range(
                        0,
                        questionUpdateRequest.questionOptionUpdateRequests()
                            .size()
                    )
                    .mapToObj(i -> QuestionOptionUpdateCommand.from(
                        questionUpdateRequest.questionOptionUpdateRequests()
                            .get(i),
                        i
                    ))
                    .toList()
            );
        }
    }

    public record QuestionOptionCreateCommand(
        String questionOptionTitle,
        Integer displayOrder
    ) {

        public static QuestionOptionCreateCommand from(
            QuestionOptionCreateRequest questionOptionCreateRequest,
            Integer displayOrder
        ) {
            return new QuestionOptionCreateCommand(
                questionOptionCreateRequest.questionOptionTitle(),
                displayOrder
            );
        }
    }

    public record QuestionOptionUpdateCommand(
        Long questionOptionId,
        String questionOptionTitle,
        Integer displayOrder
    ) {

        public static QuestionOptionUpdateCommand from(
            QuestionOptionUpdateRequest questionOptionUpdateRequest,
            Integer displayOrder
        ) {
            return new QuestionOptionUpdateCommand(
                questionOptionUpdateRequest.questionOptionId(),
                questionOptionUpdateRequest.questionOptionTitle(),
                displayOrder
            );
        }
    }
}
