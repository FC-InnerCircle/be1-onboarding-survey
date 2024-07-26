package fastcampus.innercircle.onboarding.survey.repository;

import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SurveyFormRepository extends JpaRepository<SurveyForm, Long> {
    Optional<SurveyForm> findTop1ByIdOrderByVersionDesc(Long id);
}
