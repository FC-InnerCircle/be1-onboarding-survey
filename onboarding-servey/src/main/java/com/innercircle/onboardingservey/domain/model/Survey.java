package com.innercircle.onboardingservey.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "serveys")
@RequiredArgsConstructor
@Getter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;
    private final String title;
    private final String description;

}
