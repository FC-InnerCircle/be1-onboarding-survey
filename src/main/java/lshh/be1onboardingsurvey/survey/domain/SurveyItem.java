package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.*;
import lshh.be1onboardingsurvey.common.BooleanConverter;

@Entity
public class SurveyItem {
    @EmbeddedId
    SurveyItemId id;
    String name;
    String description;
    @Enumerated(EnumType.STRING)
    SurveyItemForm form;
    @Convert(converter = BooleanConverter.class)
    Boolean required;

    public void upgradeVersion(){
        this.id.upgradeVersion();
    }
}
