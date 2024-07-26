package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString(exclude = {"form"})
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SURVEY_QUESTION")
public class SurveyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID")
    private Long id;

    @Column(name = "FORM_VERSION")
    private Long version;

    @Enumerated(EnumType.STRING)
    @Column(name = "RESPONSE_TYPE")
    private SurveyResponseType responseType;

    @Column(name = "QUESTION_TITLE")
    private String title;

    @Column(name = "QUESTION_DESC")
    private String desc;

    @Column(name = "IS_REQUIRED")
    private boolean isRequired;

    @Column(name = "POSITION")
    private Integer position;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FORM_ID")
    private SurveyForm form;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SurveyQuestionOption> options = new ArrayList<>();

    @Builder
    public SurveyQuestion(
            final SurveyForm form,
            final Long version,
            final String title,
            final String desc,
            final boolean isRequired,
            final SurveyResponseType responseType,
            final Integer position,
            final List<SurveyQuestionOption> options
    ) {
        this.form = form;
        this.version = version;
        this.title = title;
        this.desc = desc;
        this.isRequired = isRequired;
        this.responseType = responseType;
        this.position = position;
        options.forEach(this::addOption);
    }

    public void addOption(final SurveyQuestionOption option) {
        options.add(option);
        option.setQuestion(this);
    }
}
