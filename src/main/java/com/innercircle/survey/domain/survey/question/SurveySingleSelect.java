package com.innercircle.survey.domain.survey.question;

import com.innercircle.survey.domain.AbstractEntity;
import com.innercircle.survey.domain.survey.Survey;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "survey_single_select")
public class SurveySingleSelect extends SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "question")
    private List<SingleSelectOption> options = new ArrayList<>();
}
