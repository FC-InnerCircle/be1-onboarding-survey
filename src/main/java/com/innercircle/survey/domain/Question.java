package com.innercircle.survey.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private String text;

    private Integer version = 1;
    
    private boolean isRequired;

    private boolean isDeleted = false;

    public void setVersion(Integer version) {
        this.version = version;
    }

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options;
}
