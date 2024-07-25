package com.innercircle.survey.infrastruture.survey;

import com.innercircle.survey.infrastruture.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "servey")
public class SurveyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;

    private String surveyName;

    private String surveyDescription;
}
