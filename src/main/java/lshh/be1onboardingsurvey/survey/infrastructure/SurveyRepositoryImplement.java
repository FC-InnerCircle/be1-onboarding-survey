package lshh.be1onboardingsurvey.survey.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.be1onboardingsurvey.survey.domain.Survey;
import lshh.be1onboardingsurvey.survey.domain.component.SurveyRepository;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyView;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SurveyRepositoryImplement implements SurveyRepository {

    private final SurveyJpaRepository jpaRepository;

    @Override
    public List<SurveyView> findAllToView() {
        List<Survey> list = jpaRepository.findAll();
        return list.stream().map(SurveyView::of).toList();
    }

    @Override
    public Result save(Survey survey) {
        jpaRepository.save(survey);
        return Result.success();
    }

    @Override
    public Optional<Survey> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Optional<Survey> findByName(String name) {
        return jpaRepository.findByName(name);
    }
}
