package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.*;
import com.innercircle.onboardingservey.domain.model.SurveyResults.SurveyResult;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.innercircle.onboardingservey.domain.model.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor public class SurveyService {

    private final QuestionStore questionStore;
    private final QuestionReader questionReader;
    private final SurveyStore surveyStore;
    private final SurveyReader surveyReader;
    private final AnswerStore answerStore;

    @Transactional
    public SurveyResult createSurvey(SurveyCommand.SurveyVersionCreateCommand command) {
        final Survey survey = surveyStore.store(new Survey());
        return createSurveyVersion(
            command,
            survey
        );
    }


    @Transactional
    public SurveyResult addSurveyVersion(
        Long surveyId,
        SurveyCommand.SurveyVersionCreateCommand command
    ) {

        final Survey survey = surveyReader.getSurveyBySurveyId(surveyId);
        return createSurveyVersion(
            command,
            survey
        );
    }

    @Transactional(readOnly = true)
    public SurveyResult getSurveyLatestVersion(
        final Long surveyId
    ) {
        final Survey survey = surveyReader.getSurveyBySurveyId(surveyId);
        final List<SurveyVersion> surveyVersions = surveyReader.getSurveyVersionAllBySurveyId(survey);
        final SurveyVersion latestSurveyVersion = surveyVersions.stream()
            .sorted(Comparator.comparingLong(SurveyVersion::getSurveyVersionId))
            .toList()
            .get(0);

        final List<Question> questions = questionReader.findQuestionsBySurveyVersion(latestSurveyVersion);

        return SurveyResult.from(
            survey.getSurveyId(),
            latestSurveyVersion,
            questions
        );
    }

    @Transactional
    public SurveyResults.SurveyAnswerResult createAnswer(
        final AnswerCommand.AnswerCreateCommand command
    ) {
        final SurveyVersion surveyVersion = surveyReader.getSurveyVersionBySurveyVersionId(command.surveyVersionId());

        final Map<Long, AnswerCommand.QuestionAnswerCommand> questionAnswerCommandByQuestionIdMap = command.questionAnswerCommand()
            .stream()
            .collect(Collectors.toMap(
                it -> it.QuestionCommand()
                    .questionId(),
                Function.identity()
            ));

        final List<Question> questions = questionReader.findQuestionsBySurveyVersion(surveyVersion);
        final Answer answer = answerStore.store(new Answer(
            command.userId(),
            surveyVersion
        ));

        final Map<Long, List<QuestionAnswerDetail>> questionAnswerDetailByQuestionIdMap = questions
            .stream()
            .map(question ->
                createQuestionAnswer(
                    command,
                    questionAnswerCommandByQuestionIdMap.get(question.getQuestionId()),
                    answer,
                    question
                ))
            .collect(Collectors.toMap(
                questionAnswerDetails -> questionAnswerDetails.get(0)
                    .getQuestionAnswer()
                    .getQuestion()
                    .getQuestionId(),
                Function.identity()
            ));

        return SurveyResults.SurveyAnswerResult.from(
            command.userId(),
            surveyVersion,
            questions,
            questionAnswerDetailByQuestionIdMap
            );
    }

    private List<QuestionAnswerDetail> createQuestionAnswer(
        AnswerCommand.AnswerCreateCommand command,
        AnswerCommand.QuestionAnswerCommand questionAnswerCommand,
        Answer answer,
        Question question
    ) {
        final QuestionAnswer questionAnswer = answerStore.store(new QuestionAnswer(
            command.userId(),
            questionAnswerCommand.QuestionCommand()
                .questionType(),
            answer,
            question
        ));

        return answerStore.store(QuestionAnswerDetailFactory.create(
            questionAnswerCommand,
            questionAnswer
        ));
    }

    private SurveyResult createSurveyVersion(
        SurveyCommand.SurveyVersionCreateCommand command,
        Survey survey
    ) {
        final SurveyVersion surveyVersion = new SurveyVersion(
            command.surveyTitle(),
            command.surveyDescription(),
            survey
        );

        final List<Question> questions = questionStore.store(command.questionCreateRequests()
            .stream()
            .map(questionCreateCommand -> QuestionFactory.create(
                surveyVersion,
                questionCreateCommand
            ))
            .toList());

        return SurveyResult.from(
            survey.getSurveyId(),
            surveyVersion,
            questions
        );
    }

}
