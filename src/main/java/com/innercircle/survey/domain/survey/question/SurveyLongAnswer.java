package com.innercircle.survey.domain.survey.question;

import com.innercircle.survey.domain.AbstractEntity;
import com.innercircle.survey.domain.survey.Survey;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "survey_long_answer")
public class SurveyLongAnswer extends SurveyQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
