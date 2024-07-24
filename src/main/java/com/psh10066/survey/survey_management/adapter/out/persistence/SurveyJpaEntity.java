package com.psh10066.survey.survey_management.adapter.out.persistence;

import com.psh10066.survey.survey_management.domain.Survey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Entity
@Table(name = "survey")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SurveyJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private Long latestVersion;

    public static SurveyJpaEntity from(Survey survey) {
        return new SurveyJpaEntity(
            survey.getId().value(),
            survey.getForm().getVersion()
        );
    }
}
