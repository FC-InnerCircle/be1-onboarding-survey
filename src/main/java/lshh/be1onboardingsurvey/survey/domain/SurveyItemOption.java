package lshh.be1onboardingsurvey.survey.domain;

import jakarta.persistence.*;

@Entity
public class SurveyItemOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    Long sequence;

    @ManyToOne
    SurveyItem surveyItem;
}
