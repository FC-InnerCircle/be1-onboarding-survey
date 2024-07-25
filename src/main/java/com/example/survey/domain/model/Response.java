package com.example.survey.domain.model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Survey survey;

    @ElementCollection
    private Map<Long, String> answers;
}
