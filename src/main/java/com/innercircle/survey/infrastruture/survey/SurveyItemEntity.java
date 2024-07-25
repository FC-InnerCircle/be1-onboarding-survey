package com.innercircle.survey.infrastruture.survey;


import com.innercircle.survey.infrastruture.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "servey_item")
public class SurveyItemEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyItemId;

    private String surveyItemName;

    private String surveyItemDescription;

    private boolean required;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SurveyEntity surveyEntity;

}
