package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SURVEY_FORM")
public class SurveyForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FORM_ID")
    private Long id;

    @Column(name = "FORM_VERSION")
    private Long version;

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
            final Long version,
            final String title,
            final String desc,
            final LocalDateTime createAt,
            final List<SurveyQuestion> questions
    ) {
        this.version = version;
        this.title = title;
        this.desc = desc;
        this.createAt = createAt;
        questions.forEach(this::addQuestion);
    }

    public void addQuestion(SurveyQuestion question) {
        questions.add(question);
        question.setForm(this);
    }
}
