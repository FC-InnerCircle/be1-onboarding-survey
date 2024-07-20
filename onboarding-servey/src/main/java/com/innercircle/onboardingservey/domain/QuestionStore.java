package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.Question;
import java.util.List;

public interface QuestionStore {

    List<Question> store(List<Question> questions);
}
