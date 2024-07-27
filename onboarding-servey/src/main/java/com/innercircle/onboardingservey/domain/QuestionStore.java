package com.innercircle.onboardingservey.domain;

import com.innercircle.onboardingservey.domain.model.entity.Question;
import java.util.Collection;
import java.util.List;

public interface QuestionStore {

    List<Question> store(List<Question> questions);
    void deleteByQuestionIds(Collection<Long> questionIds);
}
