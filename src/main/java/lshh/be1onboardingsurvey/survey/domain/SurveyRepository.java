package lshh.be1onboardingsurvey.survey.domain;

import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyView;

import java.util.List;

public interface SurveyRepository {

    List<SurveyView> findAllToView();

    Result save(Survey survey);
}
