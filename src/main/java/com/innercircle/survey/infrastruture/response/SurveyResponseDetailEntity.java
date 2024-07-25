package com.innercircle.survey.infrastruture.response;

import com.innercircle.survey.infrastruture.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "servey_response_detail")
public class SurveyResponseDetailEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long responseDetailId;

    @ManyToOne
    @JoinColumn(name = "response_id",  foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private SurveyResponseEntity response;


    private String responseValue;
}
