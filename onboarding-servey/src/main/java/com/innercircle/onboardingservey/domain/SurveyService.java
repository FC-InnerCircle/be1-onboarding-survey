package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.Question;
import com.innercircle.onboardingservey.domain.model.QuestionFactory;
import com.innercircle.onboardingservey.domain.model.Survey;
import com.innercircle.onboardingservey.domain.model.SurveyCommand;
import com.innercircle.onboardingservey.domain.model.SurveyResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SurveyService {

    private final QuestionStore questionStore;
    private final SurveyStore surveyStore;

    @Transactional
    public SurveyResult.SurveyDetail create(SurveyCommand.SurveyCreateCommand command) {

        final Survey survey = surveyStore.store(
            new Survey(command.surveyTitle(), command.surveyDescription()));

        final List<Question> questions = questionStore.store(
            command.questionCreateRequests()
                .stream()
                .map(questionCreateCommand -> QuestionFactory.from(survey, questionCreateCommand))
                .toList()
        );

        return SurveyResult.SurveyDetail.from(survey, questions);
    }


}
