package com.innercircle.survey.infrastruture.survey;


import com.innercircle.survey.infrastruture.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "select_option")
public class SurveyItemOptionEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_item_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SurveyItemEntity surveyItemEntity;

    @Enumerated(EnumType.STRING)
    private ItemInputFormat inputFormat;

    public enum ItemInputFormat {
        SHORT_ANSWER,
        LONG_ANSWER,
        SINGLE_SELECTION,
        MULTIPLE_SELECTION
    }
}
