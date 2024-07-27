package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SURVEY_RESULT_DETAIL")
public class SurveyResultDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESULT_DETAIL_ID")
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESULT_ID")
    private SurveyResult result;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private SurveyQuestion question;

    @OneToMany(mappedBy = "resultDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SurveyResultDetailValue> values = new ArrayList<>();

    @Builder
    public SurveyResultDetail(final SurveyQuestion question, final List<SurveyResultDetailValue> values) {
        this.question = question;
        values.forEach(this::addValue);
    }

    private void addValue(final SurveyResultDetailValue value) {
        values.add(value);
        value.setResultDetail(this);
    }
}
