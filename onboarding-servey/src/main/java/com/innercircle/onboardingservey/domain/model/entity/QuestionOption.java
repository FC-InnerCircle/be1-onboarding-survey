package com.innercircle.onboardingservey.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "questions_options")
@Getter
@NoArgsConstructor
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionOptionId;
    private String title;
    private Integer displayOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public QuestionOption(
        String title,
        Integer displayOrder
    ) {
        this.title = title;
        this.displayOrder = displayOrder;
    }
}
