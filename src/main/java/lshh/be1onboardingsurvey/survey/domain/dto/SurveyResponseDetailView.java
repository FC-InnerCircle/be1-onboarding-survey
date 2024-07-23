package lshh.be1onboardingsurvey.survey.domain.dto;

import lshh.be1onboardingsurvey.survey.domain.*;

import java.util.List;
import java.util.Optional;

public record SurveyResponseDetailView (
        Long id,
        Long surveyId,
        String surveyName,
        String surveyDescription,
        List<SurveyResponseItemDetailView> items
){
    public static SurveyResponseDetailView of(SurveyResponse surveyResponse) {
        Survey survey = surveyResponse.getSurvey();
        List<SurveyResponseItem> items = surveyResponse.getItems();
        // todo - 좀.. 짜침... 다른 고도화를 생각해보자. projection 바로 레포지토리에서 가져오기 vs response와 item에게 각각 일 나눠 시키기. 하이버네이트가 찍어주는 쿼리 한번 보자..
        List<SurveyResponseItemDetailView> itemDetails = items.stream()
                .map(responseItem -> {
                    SurveyItem item = survey.findItem(responseItem.getSurveyItemId()).orElse(null);
                    if(item == null){
                        return null;
                    }
                    Object realValue = switch (item.getFormType()){
                        case TEXT, TEXTAREA -> responseItem.getRealValue();
                        case RADIO -> {
                            if(responseItem.getRealValue() != null && responseItem.getRealValue() instanceof Long value){
                                yield item.findOption(value)
                                        .map(SurveyItemOption::getName)
                                        .orElse(null);
                            }
                            yield null;
                        }
                        case CHECKBOX -> {
                            if(responseItem.getRealValue() != null && responseItem.getRealValue() instanceof List<?> values){
                                List<SurveyItemOption> options = values.stream()
                                        .map(value -> item.findOption((Long)value))
                                        .filter(Optional::isPresent)
                                        .map(Optional::get)
                                        .toList();
                                yield options.stream()
                                        .map(SurveyItemOption::getName)
                                        .toList();
                            }
                            yield null;
                        }
                    };
                    return SurveyResponseItemDetailView.of(responseItem, item, realValue);
                })
                .toList();

        return new SurveyResponseDetailView(
                surveyResponse.getId(),
                surveyResponse.getSurvey().getId(),
                surveyResponse.getSurvey().getName(),
                surveyResponse.getSurvey().getDescription(),
                itemDetails
        );
    }
}
