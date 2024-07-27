package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SURVEY_RESULT")
public class SurveyResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESULT_ID")
    private Long id;

    @Column(name = "CREATE_AT")
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "FORM_ID", referencedColumnName = "FORM_ID"),
            @JoinColumn(name = "VERSION", referencedColumnName = "VERSION")
    })
    private SurveyForm form;

    @OneToMany(mappedBy = "result", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SurveyResultDetail> details = new ArrayList<>();

    @Builder
    public SurveyResult(final SurveyForm form, final LocalDateTime createAt, final List<SurveyResultDetail> details) {
        this.form = form;
        this.createAt = createAt;
        details.forEach(this::addResultDetail);
    }

    private void addResultDetail(final SurveyResultDetail detail) {
        details.add(detail);
        detail.setResult(this);
    }
}
