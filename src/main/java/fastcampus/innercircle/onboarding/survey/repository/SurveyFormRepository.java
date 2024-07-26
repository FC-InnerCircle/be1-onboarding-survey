package fastcampus.innercircle.onboarding.survey.repository;

import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyFormRepository extends JpaRepository<SurveyForm, Long> {
}
