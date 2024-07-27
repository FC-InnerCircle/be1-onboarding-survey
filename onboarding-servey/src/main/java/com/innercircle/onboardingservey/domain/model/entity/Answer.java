package com.innercircle.onboardingservey.domain.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "answer")
@NoArgsConstructor
@Getter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyVersion surveyVersion;

    public Answer(
        Long userId,
        SurveyVersion surveyVersion
    ) {
        this.userId = userId;
        this.surveyVersion = surveyVersion;
    }
}
