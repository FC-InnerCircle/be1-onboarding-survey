package com.innercircle.survey.infrastruture.survey;


import com.innercircle.survey.domain.survey.SurveyItem;
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
@Table(name = "survey_item")
public class SurveyItemEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SurveyEntity survey;

    @Enumerated(EnumType.STRING)
    private SurveyItem.ItemInputFormat inputFormat;

    private String surveyItemName;

    private String surveyItemDescription;

    private boolean itemRequired;

    @CreatedDate
    private LocalDateTime createdAt;


    public static SurveyItemEntity toEntity(SurveyItem surveyItem) {
        return SurveyItemEntity.builder()
                .surveyItemId(surveyItem.getSurveyItemId() != null ? surveyItem.getSurveyItemId() : null)
                .survey(SurveyEntity.toEntity(surveyItem.getSurvey()))
                .surveyItemName(surveyItem.getSurveyItemName())
                .surveyItemDescription(surveyItem.getSurveyItemDescription())
                .inputFormat(surveyItem.getInputFormat())
                .itemRequired(surveyItem.isItemRequired())
                .createdAt(surveyItem.getCreatedAt())
                .build();
    }

    public SurveyItem toDomain() {

        return SurveyItem.builder()
                .surveyItemId(surveyItemId)
                .survey(survey.toDomain())
                .surveyItemName(surveyItemName)
                .surveyItemDescription(surveyItemDescription)
                .inputFormat(inputFormat)
                .itemRequired(itemRequired)
                .createdAt(createdAt)
                .build();
    }
}
