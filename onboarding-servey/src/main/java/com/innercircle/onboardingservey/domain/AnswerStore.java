package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.entity.Answer;
import com.innercircle.onboardingservey.domain.model.entity.QuestionAnswer;
import com.innercircle.onboardingservey.domain.model.entity.QuestionAnswerDetail;

import java.util.List;

public interface AnswerStore {
    Answer store(Answer answer);
    QuestionAnswer store(QuestionAnswer questionAnswer);
    List<QuestionAnswerDetail> store(List<QuestionAnswerDetail> questionAnswerDetails);
}
