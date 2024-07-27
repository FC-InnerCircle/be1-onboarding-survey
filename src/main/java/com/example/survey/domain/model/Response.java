package com.example.survey.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Survey survey;

    @ElementCollection
    private Map<Long, String> answers;

    private int surveyVersion;
}
