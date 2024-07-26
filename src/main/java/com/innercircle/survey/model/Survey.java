package com.innercircle.survey.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Survey extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @Setter
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions = new ArrayList<>();

    private Integer version;

    @Builder
    private Survey(String title, String description) {
        this.title = title;
        this.description = description;
        this.version = 0;
    }

    public Integer update(String title, String description) {
        this.title = title;
        this.description = description;
        return ++this.version;
    }
}
