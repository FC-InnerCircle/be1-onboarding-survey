package com.innercircle.survey.domain.survey.response;

import com.innercircle.survey.domain.AbstractEntity;
import com.innercircle.survey.domain.survey.Survey;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "survey_responses")
public class SurveyResponse extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "response")
    private List<QuestionResponse> questionResponses = new ArrayList<>();
}
