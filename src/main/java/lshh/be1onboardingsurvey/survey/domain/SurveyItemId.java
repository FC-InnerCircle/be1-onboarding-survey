package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;

@Getter
@Embeddable
public class SurveyItemId {
    Long surveyId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long itemId;
    Long version;

    public SurveyItemId() {
    }

    public SurveyItemId(Long surveyId, Long itemId) {
        this.surveyId = surveyId;
        this.itemId = itemId;
        this.version = 0L;
    }

    public SurveyItemId(Long surveyId, Long itemId, Long version) {
        this.surveyId = surveyId;
        this.itemId = itemId;
        this.version = version;
    }

    public void upgradeVersion(){
        this.version++;
    }
}
