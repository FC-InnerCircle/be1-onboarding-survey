package com.innercircle.survey.infrastruture.survey;

import com.innercircle.survey.domain.survey.SurveyResponseDetail;
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
@Table(name = "survey_response_detail")
public class SurveyResponseDetailEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responseDetailId;

    @ManyToOne
    @JoinColumn(name = "survey_response_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SurveyResponseEntity surveyResponse;

    private String responseValue;

    @CreatedDate
    private LocalDateTime createdAt;

    public static SurveyResponseDetailEntity toEntity(SurveyResponseDetail responseDetail) {
        return SurveyResponseDetailEntity.builder()
                .responseDetailId(responseDetail.getResponseDetailId() != null ? responseDetail.getResponseDetailId() : null)
                .surveyResponse(SurveyResponseEntity.toEntity(responseDetail.getResponse()))
                .responseValue(responseDetail.getResponseValue())
                .createdAt(responseDetail.getCreatedAt())
                .build();
    }

    public SurveyResponseDetail toDomain() {
        return SurveyResponseDetail.builder()
                .responseDetailId(responseDetailId)
                .response(surveyResponse.toDomain())
                .responseValue(responseValue)
                .createdAt(createdAt)
                .build();
    }
}
