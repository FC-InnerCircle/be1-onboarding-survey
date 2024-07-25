package com.innercircle.onboardingservey.domain.model;

import com.innercircle.onboardingservey.domain.model.SurveyCommand.QuestionCreateCommand;
import com.innercircle.onboardingservey.domain.model.SurveyCommand.QuestionUpdateCommand;

public class QuestionFactory {

    public static Question create(
        final Survey survey,
        final QuestionCreateCommand command
    ) {
        return switch (command.questionType()) {
            case LONG_TEXT -> Question.longText(
                command.questionTitle(),
                command.questionDescription(),
                command.required(),
                command.displayOrder(),
                survey
            );
            case SHORT_TEXT -> Question.shortText(
                command.questionTitle(),
                command.questionDescription(),
                command.required(),
                command.displayOrder(),
                survey
            );
            case SINGLE_CHOICE -> Question.singleChoice(
                command.questionTitle(),
                command.required(),
                command.displayOrder(),
                survey,
                command.questionOptionCreateCommands()
                    .stream()
                    .map(questionOptionCreateCommand -> new QuestionOption(
                        questionOptionCreateCommand.questionOptionTitle(),
                        questionOptionCreateCommand.displayOrder()
                    ))
                    .toList()
            );
            case MULTIPLE_CHOICE -> Question.multiChoice(
                command.questionTitle(),
                command.required(),
                command.displayOrder(),
                survey,
                command.questionOptionCreateCommands()
                    .stream()
                    .map(questionOptionCreateCommand -> new QuestionOption(
                        questionOptionCreateCommand.questionOptionTitle(),
                        questionOptionCreateCommand.displayOrder()
                    ))
                    .toList()
            );
        };
    }

    public static Question update(
        final Survey survey,
        final QuestionUpdateCommand command
    ) {
        return switch (command.questionType()) {
            case LONG_TEXT -> Question.longText(
                command.questionTitle(),
                command.questionDescription(),
                command.required(),
                command.displayOrder(),
                survey
            );
            case SHORT_TEXT -> Question.shortText(
                command.questionTitle(),
                command.questionDescription(),
                command.required(),
                command.displayOrder(),
                survey
            );
            case SINGLE_CHOICE -> Question.singleChoice(
                command.questionTitle(),
                command.required(),
                command.displayOrder(),
                survey,
                command.questionOptionUpdateCommands()
                    .stream()
                    .map(questionOptionCreateCommand -> new QuestionOption(
                        questionOptionCreateCommand.questionOptionTitle(),
                        questionOptionCreateCommand.displayOrder()
                    ))
                    .toList()
            );
            case MULTIPLE_CHOICE -> Question.multiChoice(
                command.questionTitle(),
                command.required(),
                command.displayOrder(),
                survey,
                command.questionOptionUpdateCommands()
                    .stream()
                    .map(questionOptionCreateCommand -> new QuestionOption(
                        questionOptionCreateCommand.questionOptionTitle(),
                        questionOptionCreateCommand.displayOrder()
                    ))
                    .toList()
            );
        };
    }
}
