package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.SurveyItem;
import lshh.be1onboardingsurvey.survey.domain.SurveyItemOption;

import java.util.Comparator;
import java.util.List;

public record SurveyItemView(
        Long id,
        String name,
        String description,
        String formType,
        Boolean required,
        Long sequence,
        List<SurveyItemOptionView> options
) {
    public static SurveyItemView of(SurveyItem entity){
        return new SurveyItemView(entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getFormType().name(),
                entity.getRequired(),
                entity.getSequence(),
                entity.getOptions().stream()
                        .filter(option->option.getOverridden() == null)
                        .sorted(Comparator.comparing(SurveyItemOption::getSequence))
                        .map(SurveyItemOptionView::of)
                        .toList()
        );
    }
}
