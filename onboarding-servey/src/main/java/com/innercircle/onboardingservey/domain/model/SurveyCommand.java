package com.innercircle.onboardingservey.domain.model;

import com.innercircle.onboardingservey.controller.survey.SurveyAdminRequest.QuestionCreateAdminRequest;
import com.innercircle.onboardingservey.controller.survey.SurveyAdminRequest.QuestionOptionCreateAdminRequest;

import com.innercircle.onboardingservey.controller.survey.SurveyAdminRequest.SurveyCreateAdminRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.IntStream;

public class SurveyCommand {

    public record SurveyVersionCreateCommand(
        String surveyTitle,
        String surveyDescription,
        List<QuestionCreateCommand> questionCreateRequests
    ) {

        public static SurveyVersionCreateCommand from(SurveyCreateAdminRequest surveyCreateAdminRequest) {
            return new SurveyVersionCreateCommand(
                surveyCreateAdminRequest.surveyTitle(),
                surveyCreateAdminRequest.surveyDescription(),
                CollectionUtils.isEmpty(surveyCreateAdminRequest.questionCreateAdminRequests())
                    ? null
                    : IntStream.range(
                        0,
                        surveyCreateAdminRequest.questionCreateAdminRequests()
                            .size()
                    )
                    .mapToObj(i ->
                        QuestionCreateCommand.from(
                            surveyCreateAdminRequest.questionCreateAdminRequests()
                                .get(i),
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
            QuestionCreateAdminRequest questionCreateAdminRequest,
            Integer displayOrder
        ) {
            return new QuestionCreateCommand(
                questionCreateAdminRequest.questionTitle(),
                questionCreateAdminRequest.questionDescription(),
                questionCreateAdminRequest.isRequired(),
                QuestionType.valueOf(questionCreateAdminRequest.questionType()),
                displayOrder,
                CollectionUtils.isEmpty(questionCreateAdminRequest.questionOptionCreateAdminRequests())
                    ? null
                    : IntStream.range(
                        0,
                        questionCreateAdminRequest.questionOptionCreateAdminRequests()
                            .size()
                    )
                    .mapToObj(i -> QuestionOptionCreateCommand.from(
                        questionCreateAdminRequest.questionOptionCreateAdminRequests()
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
            QuestionOptionCreateAdminRequest questionOptionCreateAdminRequest,
            Integer displayOrder
        ) {
            return new QuestionOptionCreateCommand(
                questionOptionCreateAdminRequest.questionOptionTitle(),
                displayOrder
            );
        }
    }
}
