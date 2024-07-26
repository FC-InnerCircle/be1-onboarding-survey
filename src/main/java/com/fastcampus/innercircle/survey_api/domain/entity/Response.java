package com.fastcampus.innercircle.survey_api.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long responseId;

    @Column(name = "form_id", nullable = false)
    private Long formId;

    @OneToMany(mappedBy = "response", cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Long getResponseId() {
        return responseId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setFormId(Long formId) {
        this.formId = formId;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
