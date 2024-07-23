package com.innercircle.onboardingservey.domain;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.innercircle.onboardingservey.domain.model.Question;
import com.innercircle.onboardingservey.domain.model.QuestionFactory;
import com.innercircle.onboardingservey.domain.model.Survey;
import com.innercircle.onboardingservey.domain.model.SurveyCommand;
import com.innercircle.onboardingservey.domain.model.SurveyResult.SurveyDetailResult;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final QuestionStore questionStore;
    private final QuestionReader questionReader;
    private final SurveyStore surveyStore;
    private final SurveyReader surveyReader;

    @Transactional
    public SurveyDetailResult create(SurveyCommand.SurveyCreateCommand command) {

        final Survey survey = surveyStore.store(
            new Survey(
                command.surveyTitle(),
                command.surveyDescription()
            ));

        final List<Question> questions = questionStore.store(
            command.questionCreateRequests()
                .stream()
                .map(questionCreateCommand -> QuestionFactory.create(
                    survey,
                    questionCreateCommand
                ))
                .toList()
        );

        return SurveyDetailResult.from(
            survey,
            questions
        );
    }

    @Transactional
    public SurveyDetailResult update(SurveyCommand.SurveyUpdateCommand command) {

        final Survey survey = surveyReader.getBySurveyId(command.surveyId());

        final Map<Long, Question> beforeQuestionMap = questionReader.findBySurvey(survey)
            .stream()
            .collect(Collectors.toMap(
                Question::getQuestionId,
                Function.identity()
            ));
        final Map<Long, Question> updateQuestionMap = command.questionUpdateCommands()
            .stream()
            .map(updateCommand -> QuestionFactory.update(
                survey,
                updateCommand
            ))
            .collect(Collectors.toMap(
                Question::getQuestionId,
                Function.identity()
            ));

        // updateQuestion - beforeQuestion remove
        questionStore.deleteByQuestionIds(
            Sets.difference(
                beforeQuestionMap.keySet(),
                updateQuestionMap.keySet()
            )
        );

        // beforeQuestion - updateQuestion add
        questionStore.store(
            Sets.difference(
                    beforeQuestionMap.keySet(),
                    updateQuestionMap.keySet()
                )
                .stream()
                .map(updateQuestionMap::get)
                .filter(Objects::nonNull)
                .toList()
        );

        // beforeQuestion == updateQuestion update
        Sets.intersection(
                beforeQuestionMap.keySet(),
                updateQuestionMap.keySet()
            )
            .stream()
            .filter(beforeQuestionMap::containsKey)
            .filter(updateQuestionMap::containsKey)
            .map(beforeQuestionMap::get)
            .forEach(question -> question.update(updateQuestionMap.get(question.getQuestionId()))
            );

        final List<Question> questions = questionReader.findBySurvey(survey);

        return SurveyDetailResult.from(
            survey,
            questions
        );
    }


}
