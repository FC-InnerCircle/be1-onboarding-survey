package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.*;
import com.innercircle.onboardingservey.domain.model.SurveyResult.SurveyDetailResult;

import java.util.*;

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
    public SurveyDetailResult createSurvey(SurveyCommand.SurveyVersionCreateCommand command) {
        final Survey survey = surveyStore.store(new Survey());
        return createSurveyVersion(
            command,
            survey
        );
    }


    @Transactional
    public SurveyDetailResult addSurveyVersion(
        Long surveyId,
        SurveyCommand.SurveyVersionCreateCommand command
    ) {

        final Survey survey = surveyReader.getSurveyBySurveyId(surveyId);
        return createSurveyVersion(
            command,
            survey
        );
    }

    private SurveyDetailResult createSurveyVersion(
        SurveyCommand.SurveyVersionCreateCommand command,
        Survey survey
    ) {
        final SurveyVersion surveyVersion = new SurveyVersion(
            command.surveyTitle(),
            command.surveyDescription(),
            survey
        );

        final List<Question> questions = questionStore.store(
            command.questionCreateRequests()
                .stream()
                .map(questionCreateCommand -> QuestionFactory.create(
                    surveyVersion,
                    questionCreateCommand
                ))
                .toList()
        );

        return SurveyDetailResult.from(
            survey.getSurveyId(),
            surveyVersion,
            questions
        );
    }

}
