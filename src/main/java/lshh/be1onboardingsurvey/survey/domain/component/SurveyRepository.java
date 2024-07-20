package lshh.be1onboardingsurvey.survey.domain.component;

import lshh.be1onboardingsurvey.survey.domain.Survey;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyView;

import java.util.List;
import java.util.Optional;

public interface SurveyRepository {

    List<SurveyView> findAllToView();

    Result save(Survey survey);

    Optional<Survey> findById(Long id);

    Optional<Survey> findByName(String name);
}
