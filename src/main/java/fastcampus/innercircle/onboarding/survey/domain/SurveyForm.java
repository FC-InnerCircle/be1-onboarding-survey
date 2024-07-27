package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SURVEY_FORM")
public class SurveyForm {
    @EmbeddedId
    private FormId formId;

    @Column(name = "FORM_TITLE")
    private String title;

    @Column(name = "FORM_DESC")
    private String desc;

    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SurveyQuestion> questions = new ArrayList<>();

    @OneToMany(mappedBy = "form", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SurveyResponse> responses = new ArrayList<>();

    @Builder
    public SurveyForm(
            final FormId formId,
            final String title,
            final String desc,
            final LocalDateTime createAt,
            final List<SurveyQuestion> questions
    ) {
        this.formId = formId;
        this.title = title;
        this.desc = desc;
        this.createAt = createAt;
        questions.forEach(this::addQuestion);
    }

    public void setNextFormId(SurveyForm beforeForm) {
        this.formId = new FormId(beforeForm.getId(), beforeForm.getVersion());
        this.formId.versionUp();
    }

    public UUID getId() {
        return this.formId.getId();
    }

    public Long getVersion() {
        return this.formId.getVersion();
    }

    public boolean isUnchanged(SurveyForm afterForm) {
        // TODO 변경사항 diff 로직
        return false;
    }

    private void addQuestion(SurveyQuestion question) {
        questions.add(question);
        question.setForm(this);
    }
}
