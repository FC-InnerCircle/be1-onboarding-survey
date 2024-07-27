package com.innercircle.survey.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private String text;

    private Integer version;

    private Boolean isRequired;

    private Boolean isDeleted;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options;

    @PrePersist
    public void prePersist() {
        if (this.version == null) {
            this.version = 1;
        }
    }

    public void delete() {
        this.isDeleted = true;
    }

    public Question createNewVersion(String text, QuestionType type, Integer version, Boolean isRequired) {
        Question newQuestion = Question.builder()
                .survey(this.survey)
                .text(text)
                .type(type)
                .version(version)
                .isRequired(isRequired)
                .options(new ArrayList<>())
                .build();

        for (Option option : this.options) {
            Option newOption = Option.builder().question(newQuestion).text(option.getText()).build();
            newQuestion.getOptions().add(newOption);
        }

        return newQuestion;
    }
}
