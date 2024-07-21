package lshh.be1onboardingsurvey.survey.infrastructure;

import lombok.RequiredArgsConstructor;
import lshh.be1onboardingsurvey.survey.domain.Survey;
import lshh.be1onboardingsurvey.survey.domain.SurveyResponse;
import lshh.be1onboardingsurvey.survey.domain.component.SurveyRepository;
import lshh.be1onboardingsurvey.survey.domain.dto.Result;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyResponseItemView;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyResponseView;
import lshh.be1onboardingsurvey.survey.domain.dto.SurveyView;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SurveyRepositoryImplement implements SurveyRepository {

    private final SurveyJpaRepository jpaRepository;
    private final SurveyResponseJpaRepository responseJpaRepository;

    @Override
    public List<SurveyView> findAllToView() {
        List<Survey> list = jpaRepository.findAll();
        return list.stream().map(SurveyView::of).toList();
    }

    @Override
    public Result<?> save(Survey survey) {
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

    @Override
    public List<SurveyResponseView> findResponseBySurveyId(Long surveyId) {
        List<SurveyResponse> maybeResponse = responseJpaRepository.findBySurveyId(surveyId);
        List<SurveyResponseItemView> items = maybeResponse.stream()
                .map(SurveyResponse::getItems)
                .map(ArrayList::new)
                .flatMap(List::stream)
                .map(SurveyResponseItemView::of)
                .toList();
        return maybeResponse.stream().map(entity -> SurveyResponseView.of(entity.getId(), entity.getSurvey().getId(), items)).toList();
    }
}
