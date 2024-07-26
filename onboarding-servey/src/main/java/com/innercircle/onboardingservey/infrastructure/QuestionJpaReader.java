package com.innercircle.onboardingservey.infrastructure;

import com.innercircle.onboardingservey.domain.QuestionReader;
import com.innercircle.onboardingservey.infrastructure.repository.QuestionRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionJpaReader implements QuestionReader {
    private final QuestionRepository questionRepository;

}
