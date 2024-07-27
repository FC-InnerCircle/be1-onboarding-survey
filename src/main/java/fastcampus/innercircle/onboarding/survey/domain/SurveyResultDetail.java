package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SURVEY_RESULT_DETAIL")
public class SurveyResultDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESULT_DETAIL_ID")
    private Long id;

    @Column(name = "TEXT")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESULT_ID")
    private SurveyResult result;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID")
    private SurveyQuestion question;

    @OneToMany(mappedBy = "resultDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<SurveyResultDetailValue> values = new ArrayList<>();
}
