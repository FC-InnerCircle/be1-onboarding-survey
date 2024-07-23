package com.innercircle.onboardingservey.infrastructure;

import com.innercircle.onboardingservey.domain.QuestionReader;
import com.innercircle.onboardingservey.domain.model.Question;
import com.innercircle.onboardingservey.domain.model.Survey;
import com.innercircle.onboardingservey.infrastructure.repository.QuestionRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionJpaReader implements QuestionReader {
    private final QuestionRepository questionRepository;

    @Override
    public List<Question> findBySurvey(Survey survey) {
        return questionRepository.findBySurvey(survey);
    }
}
