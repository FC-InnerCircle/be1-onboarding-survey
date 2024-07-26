package com.innercircle.survey.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Response extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Setter
    @OneToMany(mappedBy = "response", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    private Integer version;

    @Builder
    private Response(Survey survey, Integer version) {
        this.survey = survey;
        this.version = version;
    }
}
