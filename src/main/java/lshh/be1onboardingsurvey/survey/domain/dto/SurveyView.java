package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.entity.Survey;

import java.util.List;

public record SurveyView(
        Long id,
        String name,
        String description,
        List<SurveyItemView> items
) {
    public static SurveyView of(Survey entity){
        return new SurveyView(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getItems().stream()
                        .filter(item->item.getVersion().overwritten() == null)
                        .map(SurveyItemView::of)
                        .toList()
        );
    }
}
