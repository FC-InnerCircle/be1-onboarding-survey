package com.innercircle.onboardingservey.domain.model;

import com.innercircle.onboardingservey.controller.survey.SurveyApiRequest;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@UtilityClass
public class AnswerCommand {
    public record AnswerCreateCommand(
        Long userId,
        Long surveyVersionId,
        List<QuestionAnswerCommand> questionAnswerCommand
    ) {
        public static AnswerCreateCommand from(SurveyApiRequest.AnswerApiCreateRequest request) {
            return new AnswerCreateCommand(
                request.userId(),
                request.surveyVersionId(),
                CollectionUtils.isEmpty(request.questionAnswerApiCreateRequests())
                    ? Collections.emptyList()
                    : IntStream.range(
                        0,
                        request.questionAnswerApiCreateRequests()
                            .size()
                    )
                    .mapToObj(i -> QuestionAnswerCommand.from(
                        request.questionAnswerApiCreateRequests()
                            .get(i),
                        i
                    ))
                    .toList()
            );
        }
    }

    public record QuestionAnswerCommand(
        QuestionCommand QuestionCommand,
        String textAnswer,
        List<Long> choiceAnswer
    ) {
        public static QuestionAnswerCommand from(
            SurveyApiRequest.QuestionAnswerApiCreateRequest request,
            int displayOrder
        ) {
            return new QuestionAnswerCommand(
                AnswerCommand.QuestionCommand.from(
                    request.questionApiRequest(),
                    displayOrder
                ),
                request.textAnswer(),
                request.choiceAnswer()
            );
        }
    }

    public record QuestionCommand(
        Long questionId,
        String questionTitle,
        String questionDescription,
        Boolean required,
        QuestionType questionType,
        Integer displayOrder,
        List<QuestionOptionCommand> questionOptionCommands
    ) {
        public static QuestionCommand from(
            SurveyApiRequest.QuestionApiRequest questionApiRequest,
            Integer displayOrder
        ) {
            return new AnswerCommand.QuestionCommand(
                questionApiRequest.questionId(),
                questionApiRequest.questionTitle(),
                questionApiRequest.questionDescription(),
                questionApiRequest.isRequired(),
                QuestionType.valueOf(questionApiRequest.questionType()),
                displayOrder,
                CollectionUtils.isEmpty(questionApiRequest.questionOptionApiRequests())
                    ? Collections.emptyList()
                    : IntStream.range(
                        0,
                        questionApiRequest.questionOptionApiRequests()
                            .size()
                    )
                    .mapToObj(i -> QuestionOptionCommand.from(
                        questionApiRequest.questionOptionApiRequests()
                            .get(i),
                        i
                    ))
                    .toList()
            );
        }
    }

    public record QuestionOptionCommand(
        String questionOptionTitle,
        Integer displayOrder
    ) {

        public static QuestionOptionCommand from(
            SurveyApiRequest.QuestionOptionApiRequest questionOptionAdminRequest,
            Integer displayOrder
        ) {
            return new QuestionOptionCommand(
                questionOptionAdminRequest.questionOptionTitle(),
                displayOrder
            );
        }
    }
}
