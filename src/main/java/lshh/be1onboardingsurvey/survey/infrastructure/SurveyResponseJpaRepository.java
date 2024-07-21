package lshh.be1onboardingsurvey.survey.infrastructure;

import lshh.be1onboardingsurvey.survey.domain.SurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyResponseJpaRepository extends JpaRepository<SurveyResponse, Long> {

    List<SurveyResponse> findBySurveyId(Long surveyId);
}
