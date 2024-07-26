package com.innercircle.survey.infrastruture.survey;

import com.innercircle.survey.domain.survey.Survey;
import com.innercircle.survey.infrastruture.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "survey")
public class SurveyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;

    private String surveyName;

    private String surveyDescription;

    @CreatedDate
    private LocalDateTime createdAt;

    public static SurveyEntity toEntity(Survey survey) {

        return SurveyEntity.builder()
                .surveyId(survey.getSurveyId() != null ? survey.getSurveyId() : null)
                .surveyName(survey.getSurveyName())
                .surveyDescription(survey.getSurveyDescription())
                .createdAt(survey.getCreatedAt())
                .build();
    }

    public Survey toDomain() {
        return Survey.builder()
                .surveyId(surveyId)
                .surveyName(surveyName)
                .surveyDescription(surveyDescription)
                .createdAt(createdAt)
                .build();
    }
}
