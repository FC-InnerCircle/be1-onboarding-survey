package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.entity.Survey;

import java.util.List;

public record SurveyAllVersionView(
        Long id,
        String name,
        String description,
        List<SurveyItemVersionView> items
) {
    public static SurveyAllVersionView of(Survey entity){
        return new SurveyAllVersionView(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getItems().stream()
                        .map(SurveyItemVersionView::of)
                        .toList()
        );
    }
}
