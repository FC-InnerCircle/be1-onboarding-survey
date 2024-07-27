package fastcampus.innercircle.onboarding.survey.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "SURVEY_RESULT_DETAIL_VALUE")
public class SurveyResultDetailValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESULT_DETAIL_VALUE_ID")
    private Long id;

    @Column(name = "OPTION_VALUE")
    private String value;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RESULT_DETAIL_ID")
    private SurveyResultDetail resultDetail;

    public SurveyResultDetailValue(final String value) {
        this.value = value;
    }
}
