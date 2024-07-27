package com.innercircle.onboardingservey.infrastructure;

import com.innercircle.onboardingservey.domain.AnswerReader;
import com.innercircle.onboardingservey.domain.model.entity.Answer;
import com.innercircle.onboardingservey.domain.model.entity.QuestionAnswer;
import com.innercircle.onboardingservey.domain.model.entity.QuestionAnswerDetail;
import com.innercircle.onboardingservey.domain.model.entity.Survey;
import com.innercircle.onboardingservey.infrastructure.repository.AnswerRepository;
import com.innercircle.onboardingservey.infrastructure.repository.QuestionAnswerRepository;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnswerJpaReader implements AnswerReader
{
    private final AnswerRepository answerRepository;
    private final QuestionAnswerRepository questionAnswerRepository;

    @Override
    public List<Answer> getAnswersBySurvey(final Survey survey) {
        return answerRepository.findAnswersBySurvey(survey);
    }

    @Override
    public List<QuestionAnswer> getQuestionAnswersInAnswers(final Collection<Answer> answers) {
        return null;
    }

    @Override
    public List<QuestionAnswerDetail> getQuestionAnswerDetailsInQuestionAnswer(
        final Collection<QuestionAnswer> questionAnswers) {
        return null;
    }
}
