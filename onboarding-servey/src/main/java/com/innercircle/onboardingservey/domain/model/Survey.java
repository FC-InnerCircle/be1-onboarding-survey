package com.innercircle.onboardingservey.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

@Entity
@Table(name = "serveys")
@NoArgsConstructor
@Getter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;
    private String title;
    private String description;

    public Survey(
        String title,
        String description
    ) {
        this.title = title;
        this.description = description;
    }

    public void update(
        String title,
        String description
    ) {
        this.title = title;
        this.description = description;
        valid();
    }

    private void valid() {

        Assert.hasText(
            this.title,
            "title must not be null"
        );
        Assert.hasText(
            this.description,
            "description must not be null"
        );
    }
}
