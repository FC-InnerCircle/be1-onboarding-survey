package fastcampus.innercircle.onboarding.survey.repository;

import fastcampus.innercircle.onboarding.survey.domain.FormId;
import fastcampus.innercircle.onboarding.survey.domain.SurveyForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface SurveyFormRepository extends JpaRepository<SurveyForm, FormId> {
    Optional<SurveyForm> findTop1ByFormId_IdOrderByFormId_VersionDesc(UUID id);
}
