package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.entity.Answer;
import com.innercircle.onboardingservey.domain.model.entity.QuestionAnswer;
import com.innercircle.onboardingservey.domain.model.entity.QuestionAnswerDetail;
import com.innercircle.onboardingservey.domain.model.entity.Survey;
import java.util.Collection;
import java.util.List;

public interface AnswerReader {
    List<Answer> getAnswersBySurvey(Survey survey);
    List<QuestionAnswer> getQuestionAnswersInAnswers(Collection<Answer> answers);
    List<QuestionAnswerDetail> getQuestionAnswerDetailsInQuestionAnswer(Collection<QuestionAnswer> questionAnswers);
}
