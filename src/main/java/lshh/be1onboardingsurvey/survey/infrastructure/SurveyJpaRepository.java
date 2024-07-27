package lshh.be1onboardingsurvey.survey.infrastructure;

import lshh.be1onboardingsurvey.survey.domain.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SurveyJpaRepository extends JpaRepository<Survey, Long> {
    List<Survey> findByName(String name);
}
