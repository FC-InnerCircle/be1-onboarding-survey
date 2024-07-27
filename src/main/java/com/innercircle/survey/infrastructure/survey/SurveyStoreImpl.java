package com.innercircle.survey.infrastructure.survey;

import com.innercircle.survey.domain.survey.Survey;
import com.innercircle.survey.domain.survey.SurveyStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SurveyStoreImpl implements SurveyStore {

    private final SurveyRepository surveyRepository;

    @Override
    public Survey store(Survey survey) {
        return surveyRepository.save(survey);
    }
}
