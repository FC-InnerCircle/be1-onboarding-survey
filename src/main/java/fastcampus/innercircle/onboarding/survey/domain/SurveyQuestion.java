package fastcampus.innercircle.onboarding.survey.domain;

import fastcampus.innercircle.onboarding.survey.exception.SurveyQuestionOptionNotAllowedException;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static fastcampus.innercircle.onboarding.survey.domain.SurveyResultType.*;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "RESULT_TYPE")
    private SurveyResultType resultType;

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
    @JoinColumns({
            @JoinColumn(name = "FORM_ID", referencedColumnName = "FORM_ID"),
            @JoinColumn(name = "VERSION", referencedColumnName = "VERSION")
    })
    private SurveyForm form;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SurveyQuestionOption> options = new ArrayList<>();

    @Builder
    public SurveyQuestion(
            final SurveyForm form,
            final String title,
            final String desc,
            final boolean isRequired,
            final SurveyResultType resultType,
            final Integer position,
            final List<SurveyQuestionOption> options
    ) {
        this.form = form;
        this.title = title;
        this.desc = desc;
        this.isRequired = isRequired;
        this.resultType = resultType;
        this.position = position;
        options.forEach(this::addOption);
    }

    public void addOption(final SurveyQuestionOption option) {
        if (resultType.isSubjectiveType()) {
            throw new SurveyQuestionOptionNotAllowedException("선택형 질문이 아닙니다.");
        }
        options.add(option);
        option.setQuestion(this);
    }

    private boolean isOptionalQuestion() {
        return MULTI.equals(resultType) || SINGLE.equals(resultType);
    }

    public Long getVersion() {
        return this.form.getVersion();
    }
}
