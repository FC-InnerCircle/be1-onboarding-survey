package com.fastcampus.innercircle.survey_api.domain.entity;

import com.fastcampus.innercircle.survey_api.domain.QuestionType;
import com.fastcampus.innercircle.survey_api.domain.request.QuestionRequest;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long questionId;

    @Column(name = "title", nullable = false)
    private String title;   // 항목 이름

    @Column(name = "description", length = 1000)
    private String description; // 항목 설명

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType type;  // 항목 입력 형태

    @Column(nullable = false)
    private boolean isRequired;   // 항목 필수 여부

    @ElementCollection
    private List<String> options;   // 단일 선택 리스트, 다중 선택 리스트에서 선택 할 수 있는 후보

    @ManyToOne
    @JoinColumn(name = "form_id", nullable = false)
    private SurveyForm form;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public SurveyForm getForm() {
        return form;
    }

    public void setForm(SurveyForm form) {
        this.form = form;
    }

    public static Question of(QuestionRequest request) {
        Question question = new Question();
        question.setTitle(request.getTitle());
        question.setDescription(request.getDescription());
        question.setType(request.getQuestionType());
        question.setRequired(request.isRequired());
        question.setOptions(request.getOptions());
        return question;
    }
}
