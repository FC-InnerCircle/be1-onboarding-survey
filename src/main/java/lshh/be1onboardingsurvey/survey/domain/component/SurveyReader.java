package lshh.be1onboardingsurvey.survey.domain.component;

import lshh.be1onboardingsurvey.survey.domain.dto.SurveyView;

import java.util.List;

public interface SurveyReader {

    List<SurveyView> findAllToView();
}
