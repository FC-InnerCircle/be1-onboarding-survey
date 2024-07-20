package lshh.be1onboardingsurvey.survey.infrastructure;

import lshh.be1onboardingsurvey.survey.domain.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SurveyJpaRepository extends JpaRepository<Survey, Long> {
    Optional<Survey> findByName(String name);
}
