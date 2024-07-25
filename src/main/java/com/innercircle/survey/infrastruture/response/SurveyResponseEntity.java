package com.innercircle.survey.infrastruture.response;

import com.innercircle.survey.infrastruture.common.BaseTimeEntity;
import com.innercircle.survey.infrastruture.survey.SurveyEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "servey_response")
public class SurveyResponseEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyResponseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SurveyEntity surveyEntity;

    private String responseShortTest;

    private String responseLongTest;

}
