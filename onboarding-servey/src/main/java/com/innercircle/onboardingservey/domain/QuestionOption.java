package com.innercircle.onboardingservey.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "questions_options")
@RequiredArgsConstructor
@Getter
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionOptionId;
    private final String title;
    private final Integer order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;
}
