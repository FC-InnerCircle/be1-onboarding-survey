package com.innercircle.onboardingservey.domain.model;

import com.innercircle.onboardingservey.controller.survey.SurveyRequest.QuestionCreateRequest;
import com.innercircle.onboardingservey.controller.survey.SurveyRequest.QuestionOptionCreateRequest;
import com.innercircle.onboardingservey.controller.survey.SurveyRequest.SurveyCreateRequest;
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
                surveyCreateRequest.questionCreateRequests()
                    .stream()
                    .map(QuestionCreateCommand::from)
                    .toList()
            );
        }
    }

    public record QuestionCreateCommand(
        String questionTitle,
        String questionDescription,
        Boolean required,
        QuestionType questionType,
        List<QuestionOptionCreateCommand> questionOptionCreateCommands
    ) {

        public static QuestionCreateCommand from(QuestionCreateRequest questionCreateRequest) {
            return new QuestionCreateCommand(
                questionCreateRequest.questionTitle(),
                questionCreateRequest.questionDescription(),
                questionCreateRequest.isRequired(),
                QuestionType.valueOf(questionCreateRequest.questionType()),
                IntStream.range(0, questionCreateRequest.questionOptionCreateRequests()
                        .size())
                    .mapToObj(i -> QuestionOptionCreateCommand.from(
                        questionCreateRequest.questionOptionCreateRequests()
                            .get(i), i))
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
                questionOptionCreateRequest.questionOptionTitle(), displayOrder);
        }
    }

}
