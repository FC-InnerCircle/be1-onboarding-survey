package com.innercircle.onboardingservey.domain.model;

import com.innercircle.onboardingservey.domain.model.entity.*;

import java.util.Collections;
import java.util.List;

public class QuestionAnswerDetailFactory {
    public static List<QuestionAnswerDetail> create(
        final AnswerCommand.QuestionAnswerCommand command,
        final QuestionAnswer questionAnswer
    ) {

        return switch (command.QuestionCommand()
            .questionType()) {

            case LONG_TEXT, SHORT_TEXT -> Collections.singletonList(
                QuestionAnswerDetail.text(
                    command.textAnswer(),
                    questionAnswer
                )
            );
            case SINGLE_CHOICE -> Collections.singletonList(
                QuestionAnswerDetail.singleChoice(
                    command.choiceAnswer()
                        .get(0),
                    questionAnswer
                )
            );
            case MULTIPLE_CHOICE -> QuestionAnswerDetail.multiChoice(
                command.choiceAnswer(),
                questionAnswer
            );
        };
    }
}
