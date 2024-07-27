package fastcampus.innercircle.onboarding.survey.repository;

import fastcampus.innercircle.onboarding.survey.domain.SurveyResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
}
