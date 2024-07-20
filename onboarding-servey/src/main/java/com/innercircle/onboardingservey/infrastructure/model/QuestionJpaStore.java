package com.innercircle.onboardingservey.infrastructure.model;

import com.innercircle.onboardingservey.domain.QuestionStore;
import com.innercircle.onboardingservey.domain.model.Question;
import com.innercircle.onboardingservey.domain.model.QuestionFactory;
import com.innercircle.onboardingservey.domain.model.SurveyCommand.QuestionCreateCommand;
import com.innercircle.onboardingservey.infrastructure.repository.QuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionJpaStore implements QuestionStore {

    private final QuestionRepository questionRepository;

    public List<Question> store(List<Question> questions) {
        return questionRepository.saveAll(questions);
    }
}
