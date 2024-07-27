package com.innercircle.onboardingservey.infrastructure;

import com.innercircle.onboardingservey.domain.AnswerStore;
import com.innercircle.onboardingservey.domain.model.entity.Answer;
import com.innercircle.onboardingservey.domain.model.entity.QuestionAnswer;
import com.innercircle.onboardingservey.domain.model.entity.QuestionAnswerDetail;
import com.innercircle.onboardingservey.infrastructure.repository.QuestionAnswerDetailRepository;
import com.innercircle.onboardingservey.infrastructure.repository.QuestionAnswerRepository;
import com.innercircle.onboardingservey.infrastructure.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnswerJpaStore implements AnswerStore {
    private final AnswerRepository answerRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final QuestionAnswerDetailRepository questionAnswerDetailRepository;
    @Override
    public Answer store(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public QuestionAnswer store(QuestionAnswer questionAnswer) {
        return questionAnswerRepository.save(questionAnswer);
    }

    @Override
    public List<QuestionAnswerDetail> store(List<QuestionAnswerDetail> questionAnswerDetails) {
        return questionAnswerDetailRepository.saveAll(questionAnswerDetails);
    }
}
