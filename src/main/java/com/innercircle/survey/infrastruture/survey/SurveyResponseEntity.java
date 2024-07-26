package com.innercircle.survey.infrastruture.survey;

import com.innercircle.survey.domain.survey.SurveyItem;
import com.innercircle.survey.domain.survey.SurveyResponse;
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
@Table(name = "survey_response")
public class SurveyResponseEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyResponseId;

    @Column(nullable = false)
    private Long surveyId;

    @Column(nullable = false)
    private Long surveyItemId;

    private String responseShortValue;

    private String responseLongValue;

    @Enumerated(EnumType.STRING)
    private SurveyItem.ItemInputFormat inputFormat;

    @CreatedDate
    private LocalDateTime createdAt;

    public static SurveyResponseEntity toEntity(SurveyResponse surveyResponse) {

        return SurveyResponseEntity.builder()
                .surveyResponseId(surveyResponse.getSurveyResponseId() != null
                        ? surveyResponse.getSurveyResponseId() : null)
                .surveyItemId(surveyResponse.getSurveyItemId())
                .surveyId(surveyResponse.getSurveyId())
                .inputFormat(surveyResponse.getInputFormat())
                .responseShortValue(surveyResponse.getResponseShortValue() != null
                        ? surveyResponse.getResponseShortValue() : null)
                .responseLongValue(surveyResponse.getResponseLongValue() != null
                        ? surveyResponse.getResponseLongValue() : null)
                .createdAt(surveyResponse.getCreatedAt())
                .build();
    }

    public SurveyResponse toDomain() {
        return SurveyResponse.builder()
                .surveyResponseId(surveyResponseId)
                .surveyId(surveyId)
                .surveyItemId(surveyItemId)
                .inputFormat(inputFormat)
                .responseShortValue(responseShortValue)
                .responseLongValue(responseLongValue)
                .createdAt(createdAt)
                .build();
    }
}
