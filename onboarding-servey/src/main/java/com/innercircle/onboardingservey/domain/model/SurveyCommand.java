package com.innercircle.onboardingservey.domain.model;

import com.innercircle.onboardingservey.controller.survey.SurveyRequest.QuestionCreateRequest;
import com.innercircle.onboardingservey.controller.survey.SurveyRequest.QuestionOptionCreateRequest;

import com.innercircle.onboardingservey.controller.survey.SurveyRequest.SurveyVersionCreateRequest;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.IntStream;

public class SurveyCommand {

    public record SurveyVersionCreateCommand(
        String surveyTitle,
        String surveyDescription,
        List<QuestionCreateCommand> questionCreateRequests
    ) {

        public static SurveyVersionCreateCommand from(SurveyVersionCreateRequest surveyVersionCreateRequest) {
            return new SurveyVersionCreateCommand(
                surveyVersionCreateRequest.surveyTitle(),
                surveyVersionCreateRequest.surveyDescription(),
                CollectionUtils.isEmpty(surveyVersionCreateRequest.questionCreateRequests())
                    ? null
                    : IntStream.range(
                        0,
                        surveyVersionCreateRequest.questionCreateRequests()
                            .size()
                    )
                    .mapToObj(i ->
                        QuestionCreateCommand.from(
                            surveyVersionCreateRequest.questionCreateRequests()
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
            QuestionCreateRequest questionCreateRequest,
            Integer displayOrder
        ) {
            return new QuestionCreateCommand(
                questionCreateRequest.questionTitle(),
                questionCreateRequest.questionDescription(),
                questionCreateRequest.isRequired(),
                QuestionType.valueOf(questionCreateRequest.questionType()),
                displayOrder,
                CollectionUtils.isEmpty(questionCreateRequest.questionOptionCreateRequests())
                    ? null
                    : IntStream.range(
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
}
