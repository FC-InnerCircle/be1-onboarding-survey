package com.innercircle.onboardingservey.domain.model;

import com.innercircle.onboardingservey.domain.model.SurveyCommand.QuestionCreateCommand;

public class QuestionFactory {

    public static Question create(
        final SurveyVersion surveyVersion,
        final QuestionCreateCommand command
    ) {
        return switch (command.questionType()) {
            case LONG_TEXT -> Question.longText(
                command.questionTitle(),
                command.questionDescription(),
                command.required(),
                command.displayOrder(),
                surveyVersion
            );
            case SHORT_TEXT -> Question.shortText(
                command.questionTitle(),
                command.questionDescription(),
                command.required(),
                command.displayOrder(),
                surveyVersion
            );
            case SINGLE_CHOICE -> Question.singleChoice(
                command.questionTitle(),
                command.required(),
                command.displayOrder(),
                surveyVersion,
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
                surveyVersion,
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
}
