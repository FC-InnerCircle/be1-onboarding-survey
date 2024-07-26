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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    private String text;

    private Integer version;

    private boolean isRequired;

    private boolean isDeleted = false;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options;

    @PrePersist
    public void prePersist() {
        this.version = 1;
    }

    @PreUpdate
    public void preUpdate() {
        this.version = this.version + 1;
    }
}
